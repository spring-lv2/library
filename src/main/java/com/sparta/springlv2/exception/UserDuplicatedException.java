package com.sparta.springlv2.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserDuplicatedException extends RuntimeException {

    public UserDuplicatedException(String message) {
        super(message);
    }
}
