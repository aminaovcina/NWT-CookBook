package com.example.recipeservice.exceptionHandling;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(Long id) {
        super("Account with ID " + id + " does not exists.");
    }
}
