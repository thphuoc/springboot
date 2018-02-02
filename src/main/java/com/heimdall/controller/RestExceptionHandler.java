package com.heimdall.controller;

import com.heimdall.model.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Controller
public class RestExceptionHandler {
    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleExceptionInternal(HttpServletResponse response, Exception ex) {
        return new ResponseEntity<>(new ErrorStatus(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
