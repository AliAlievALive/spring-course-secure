package ru.itpark.secureside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.secureside.entity.UserEntity;

import java.util.Optional;

// SimpleJpaRepository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByUsername(String username);
}
