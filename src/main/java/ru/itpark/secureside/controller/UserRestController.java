package ru.itpark.secureside.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itpark.secureside.domain.User;
import ru.itpark.secureside.dto.UserRegistrationRequestDto;
import ru.itpark.secureside.entity.UserEntity;
import ru.itpark.secureside.exception.TokenNotFoundException;
import ru.itpark.secureside.service.UserServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
  private final UserServiceImpl userService;

  @Autowired
  public UserRestController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping
  public User getByToken(@RequestParam String token) throws TokenNotFoundException {
    return userService.findByToken(token);
  }

  @GetMapping("/user")
  public Optional<UserEntity> getByLogin(@RequestParam String username) {
    return userService.findByLogin(username);
  }

  @PostMapping("/register")
  public String register(@RequestBody UserRegistrationRequestDto dto) {
    return userService.create(dto.getName(), dto.getUsername(), dto.getPassword());
  }
}
