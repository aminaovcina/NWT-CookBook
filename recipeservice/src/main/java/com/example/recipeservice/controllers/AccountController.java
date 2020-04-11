package com.example.recipeservice.controllers;

import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.AccountNotFoundException;
import com.example.recipeservice.models.Account;
import com.example.recipeservice.repositories.AccountInterface;
import com.example.recipeservice.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/account/{id}")
    private Account getAccountById(@PathVariable("id") Long id) {
        Account user = null;
        try{
            user = accountService.getAccountById(id);
        }catch(NoSuchElementException ex){
            throw new AccountNotFoundException(id);
        }
        return user;
    }
}