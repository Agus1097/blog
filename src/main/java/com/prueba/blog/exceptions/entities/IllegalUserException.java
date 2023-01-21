package com.prueba.blog.exceptions.entities;

public class IllegalUserException extends RuntimeException{
    public IllegalUserException(String message) {
        super(message);
    }
}
