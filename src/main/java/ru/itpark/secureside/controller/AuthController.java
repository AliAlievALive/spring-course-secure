package ru.itpark.secureside.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ru.itpark.secureside.api.AuthApi;
import ru.itpark.secureside.dto.User;
import ru.itpark.secureside.service.AuthService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService service;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAll() {
        return service.getAll();
    }

    @Override
    public ResponseEntity<UUID> register(User user) {
        return service.register(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public User update(User user) {
        return service.update(user);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public String changePassword(UUID userId, String password) {
        return service.changePassword(userId, password);
    }

    @Override
    public ResponseEntity validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        final var map = service.validateToken(token);
        headers.add("X-Profile", String.format("%s_%s", map.get("id"), map.get("role")));
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @Override
    public String login(User user) {
        return service.login(user.getLogin(), user.getPassword());
    }

}

