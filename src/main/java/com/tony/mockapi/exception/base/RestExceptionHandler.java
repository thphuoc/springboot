package com.tony.mockapi.exception.base;

import com.tony.mockapi.exception.LoginException;
import com.tony.mockapi.model.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity<Status> handleException(GenericException e) {
        if (e instanceof LoginException) {
            return new ResponseEntity<>(e.getStatus(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(e.getStatus(), HttpStatus.BAD_REQUEST);
    }
}
