package ru.itpark.secureside.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.secureside.domain.User;
import ru.itpark.secureside.entity.TokenEntity;
import ru.itpark.secureside.entity.UserEntity;
import ru.itpark.secureside.exception.TokenNotFoundException;
import ru.itpark.secureside.exception.UserNotFoundException;
import ru.itpark.secureside.repository.TokenRepository;
import ru.itpark.secureside.repository.UserRepository;
import ru.itpark.secureside.security.TokenGenerator;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User findByToken(String token) throws TokenNotFoundException {
        return tokenRepository.findById(token)
                .map(TokenEntity::getUser)
                .map(UserEntity::toUser)
                .orElseThrow(TokenNotFoundException::new);
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return userRepository.findByUsername(login);
    }

//    @Override
//    public void deleteByToken(String token) {
//        userRepository.deleteById(tokenRepository.findById(token)
//                .map(TokenEntity::getUser)
//                .map(UserEntity::getId).orElseThrow(UserNotFoundException::new));
//    userRepository.deleteById();
//    }

//  public User findByLogin(String login) throws TokenNotFoundException {
//    return tokenRepository.findByLogin(login)
//            .map(TokenEntity::getUser)
//            .map(UserEntity::toUser)
//            .orElseThrow(TokenNotFoundException::new);
//  }

    public String create(String name, String username, String password) {
        return create(name, username, password, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    // backend:
    // 1. Удобные (не ленивые) <- token
    // 2. Неудобные (ленивые): 201
    public String create(String name, String username, String password, Collection<GrantedAuthority> authorities) {
        final var savedUser = userRepository.save(
                new UserEntity(
                        0L,
                        name,
                        username,
                        passwordEncoder.encode(password),
                        authorities,
                        true,
                        true,
                        true,
                        true
                )
        );
        final var token = tokenGenerator.generateToken();
        tokenRepository.save(new TokenEntity(token, savedUser));
        return token;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
