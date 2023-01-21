package com.prueba.blog.exceptions.entities;

public class NotFoundAuthenticationException extends RuntimeException {
    public NotFoundAuthenticationException(String message) {
        super(message);
    }
}
