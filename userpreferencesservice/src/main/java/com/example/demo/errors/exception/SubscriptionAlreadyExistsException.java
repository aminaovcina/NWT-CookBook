package com.example.demo.errors.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SubscriptionAlreadyExistsException extends RuntimeException{
    public SubscriptionAlreadyExistsException(Integer idUser, Integer idSubUser){
        super("Subscription: " + idUser + "->"+ idSubUser+" already exists.");
    }
}