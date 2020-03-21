package com.example.demo.controllers;


import com.example.demo.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

 
@RequestMapping("account")

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

}