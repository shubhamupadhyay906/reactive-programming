package com.reactive.reactiveproject.exception;

public class NotFoundException extends Exception {

    private String message;

    public NotFoundException(String message) {
        super(message);
    }

}
