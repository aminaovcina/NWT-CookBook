package com.example.demo.errors.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message) {
        super(message);
      }
    
}