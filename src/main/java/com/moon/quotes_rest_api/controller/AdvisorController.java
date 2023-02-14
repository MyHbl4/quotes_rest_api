package com.moon.quotes_rest_api.controller;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.moon.quotes_rest_api.controller.dto.ErrorResponse;
import com.moon.quotes_rest_api.error.AuthException;
import com.moon.quotes_rest_api.error.NotFoundException;
import com.moon.quotes_rest_api.error.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AdvisorController {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException validationException) {
        return ErrorResponse.builder()
            .message(validationException.getMessage())
            .status(BAD_REQUEST)
            .timestamp(now())
            .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException notFoundException) {
        return ErrorResponse.builder()
            .message(notFoundException.getMessage())
            .status(HttpStatus.NOT_FOUND)
            .timestamp(now())
            .build();
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(FORBIDDEN)
    public ErrorResponse handleAuthException(AuthException authException) {
        return ErrorResponse.builder()
            .message(authException.getMessage())
            .status(HttpStatus.FORBIDDEN)
            .timestamp(now())
            .build();
    }
}
