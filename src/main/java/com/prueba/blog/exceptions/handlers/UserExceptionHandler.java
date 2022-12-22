package com.prueba.blog.exceptions.handlers;

import com.prueba.blog.exceptions.entities.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({
            UserAlreadyExistsException.class
    })
    public @ResponseBody ResponseEntity<?> handleUserException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
