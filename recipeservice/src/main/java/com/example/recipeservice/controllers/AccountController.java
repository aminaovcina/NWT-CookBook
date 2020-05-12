package com.example.recipeservice.controllers;

import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.AccountNotFoundException;
import com.example.recipeservice.helpers.AuthorizationHelper;
import com.example.recipeservice.helpers.UserDeseralizer;
import com.example.recipeservice.models.Account;
import com.example.recipeservice.repositories.AccountInterface;
import com.example.recipeservice.services.AccountService;
import com.example.recipeservice.services.UserHelperService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    public static final String AUTHORIZATION= "Authorization";

    @Autowired
    private AccountService accountService;
    @Autowired
    UserHelperService userHelperService;
    @Autowired
    AuthorizationHelper authorizationhelper;
    @GetMapping("/account/{id}")
    private Account getAccountById(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") Long id) {
        authorizationhelper.authorize(token);
        Account user = null;
        try{
            user = accountService.getAccountById(id);
        }catch(NoSuchElementException ex){
            throw new AccountNotFoundException(id);
        }
        return user;
    }
     
    @GetMapping("user/name/{id}")
    public UserDeseralizer getUsernameById(@PathVariable Long id) {
        UserDeseralizer dto = new UserDeseralizer();
        dto = userHelperService.getUserById(id);     
        return dto;
      }

    @GetMapping("user/validate")
    public boolean getAccountValidate(@RequestHeader(AUTHORIZATION) String token){
        return userHelperService.getAccountValidate(token);
    }

    
}