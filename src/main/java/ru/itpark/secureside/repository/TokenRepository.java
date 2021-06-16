package ru.itpark.secureside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.secureside.entity.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
}
