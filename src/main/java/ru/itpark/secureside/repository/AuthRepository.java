package ru.itpark.secureside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.secureside.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findOneByLogin(String login);

}
