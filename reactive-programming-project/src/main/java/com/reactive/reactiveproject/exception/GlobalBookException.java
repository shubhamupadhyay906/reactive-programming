package com.reactive.reactiveproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalBookException {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<?> exceptionHandler(BookException exception){
        Map<String, String> error = new HashMap<>();
        error.put("message", exception.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.ok(error);
    }
}
