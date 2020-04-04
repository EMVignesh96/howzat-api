package com.vignesh.howzat.exception.signup;

public class DuplicateUserKeyException extends RuntimeException {

    public DuplicateUserKeyException(String message) {
        super(message);
    }
}
