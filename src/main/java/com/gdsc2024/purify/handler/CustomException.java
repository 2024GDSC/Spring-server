package com.gdsc2024.purify.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    StatusCode authCode;
    public CustomException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.authCode = statusCode;
    }

}
