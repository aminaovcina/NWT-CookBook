package com.example.demo.errors.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FavoriteNotFoundException extends RuntimeException{

    public FavoriteNotFoundException(String message) {
        super(message);
    }

}