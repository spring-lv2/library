package com.sparta.springlv2.exception;

public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(String message) {
        super(message);
    }
}