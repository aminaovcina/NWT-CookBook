package com.example.recipeservice.controllers;

import com.example.recipeservice.repositories.AccountInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("account")

@RestController
public class AccountController {
    @Autowired
    private AccountInterface accountRepository;

}