package ru.itpark.secureside.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthTokenGenerateException extends RuntimeException {

    public AuthTokenGenerateException(String message) {
        super(message);
    }

}
