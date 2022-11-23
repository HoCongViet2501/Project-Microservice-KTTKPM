package com.se.gateway.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> globalExceptionHandling(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({JwtAuthenticationException.class})
    public ResponseEntity<Object> jwtAuthenticationExceptionHandling(JwtAuthenticationException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

}
