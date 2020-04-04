package com.vignesh.howzat.exception.signin;

import com.vignesh.howzat.exception.ExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class SignInExceptionHandler {
    private static final int ERR_CODE_USER_NAME_NOT_FOUND = 2001;
    private static final int ERR_CODE_PASSWORD_INCORRECT = 2002;

    @ExceptionHandler(value = {UserNameNotFoundException.class})
    public ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException exception, WebRequest request) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ExceptionEntity exceptionEntity = new ExceptionEntity(
                exception.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z")),
                ERR_CODE_USER_NAME_NOT_FOUND
        );
        return new ResponseEntity<>(exceptionEntity, notFound);
    }

    @ExceptionHandler(value = {PasswordMismatchException.class})
    public ResponseEntity<Object> handlePasswordMismatchException(PasswordMismatchException exception, WebRequest request) {
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;

        ExceptionEntity exceptionEntity = new ExceptionEntity(
                exception.getMessage(),
                unauthorized,
                ZonedDateTime.now(ZoneId.of("Z")),
                ERR_CODE_PASSWORD_INCORRECT
        );
        return new ResponseEntity<>(exceptionEntity, unauthorized);
    }
}
