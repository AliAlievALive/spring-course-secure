package ru.itpark.secureside.service;

import com.nimbusds.jwt.JWTClaimsSet;
import ru.itpark.secureside.dto.User;

public interface JwtTokenProvider {
    String generateToken(User user);

    boolean validateToken(String token);

    JWTClaimsSet parseToken(String token);
}
