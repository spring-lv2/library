package com.sparta.springlv2.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorMessage {
    private String message;
    private HttpStatus error;

    public ErrorMessage(String message, HttpStatus error) {
        this.message = message;
        this.error = error;
    }
}
