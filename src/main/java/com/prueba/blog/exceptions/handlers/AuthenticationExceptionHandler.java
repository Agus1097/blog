package com.prueba.blog.exceptions.handlers;

import com.prueba.blog.exceptions.entities.NotFoundAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler({
            NotFoundAuthenticationException.class
    })
    public @ResponseBody ResponseEntity<?> handleAuthenticationException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
