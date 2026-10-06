package com.example.price_monitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleItemAlreadyExists(
            ItemAlreadyExistsException exception
    ) {
        ApiError error = new ApiError(
                "ITEM_ALREADY_EXISTS",
                exception.getMessage(),
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
}