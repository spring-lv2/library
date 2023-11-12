package com.sparta.springlv2.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String message;
    private int status;

    public ErrorMessage(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
