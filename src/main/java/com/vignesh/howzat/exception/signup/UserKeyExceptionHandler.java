package com.vignesh.howzat.exception.signup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class UserKeyExceptionHandler {
    private static final int ERR_CODE_DUPLICATE_USER_KEY = 1001;
    private static final int ERR_CODE_USER_KEY_NOT_FOUND = 1002;
    private static final int ERR_CODE_USER_NAME_EXISTS = 1003;

    @ExceptionHandler(value = {DuplicateUserKeyException.class})
    public ResponseEntity<Object> handleDuplicateUserKeyException(DuplicateUserKeyException exception, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        UserKeyException userKeyException = new UserKeyException(
                exception.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")),
                ERR_CODE_DUPLICATE_USER_KEY
        );
        return new ResponseEntity<>(userKeyException, badRequest);
    }

    @ExceptionHandler(value = {UserKeyNotFoundException.class})
    public ResponseEntity<Object> handleUserKeyNotFoundException(UserKeyNotFoundException exception, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        UserKeyException userKeyException = new UserKeyException(
                exception.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")),
                ERR_CODE_USER_KEY_NOT_FOUND
        );
        return new ResponseEntity<>(userKeyException, badRequest);
    }

    @ExceptionHandler(value = {UserNameAlreadyExistException.class})
    public ResponseEntity<Object> handleUserNameAlreadyExistException(UserNameAlreadyExistException exception, WebRequest request) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        UserKeyException userKeyException = new UserKeyException(
                exception.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")),
                ERR_CODE_USER_NAME_EXISTS
        );
        return new ResponseEntity<>(userKeyException, badRequest);
    }
}
