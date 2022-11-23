package com.se.gateway.security.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class JwtAuthenticationException extends RuntimeException {

    private HttpStatus httpStatus;

    public JwtAuthenticationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public JwtAuthenticationException(String message) {
        super(message);
    }

}
