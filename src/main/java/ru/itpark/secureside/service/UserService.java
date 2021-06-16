package ru.itpark.secureside.service;

import org.springframework.security.authentication.AuthenticationProvider;
import ru.itpark.secureside.domain.User;
import ru.itpark.secureside.entity.UserEntity;
import ru.itpark.secureside.exception.TokenNotFoundException;

import java.util.Optional;

// TODO: -> replace with AuthenticationProvider
public interface UserService extends AuthenticationProvider {
  User findByToken(String token) throws TokenNotFoundException;

  Optional<UserEntity> findByLogin(String login);

//  void deleteByToken(String token);
}
