package com.reactive.reactiveproject.exception;

public class BookException extends Exception {

    private String message;

    public BookException(String message) {
        super(message);
    }

}
