package com.vignesh.howzat.exception.signup;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class UserKeyException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
    private final int errorCode;

    public UserKeyException(String message, HttpStatus httpStatus, ZonedDateTime timestamp, int errorCode) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
