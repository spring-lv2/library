package com.sparta.springlv2.exception;


public class CustomDuplicatedException extends RuntimeException {

    public CustomDuplicatedException(String message) {
        super(message);
    }
}
