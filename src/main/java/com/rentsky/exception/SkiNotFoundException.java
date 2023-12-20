package com.rentsky.exception;

public class SkiNotFoundException extends RuntimeException {
    public SkiNotFoundException(String message) {
        super(message);
    }
}
