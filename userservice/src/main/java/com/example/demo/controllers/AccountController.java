package com.example.demo.controllers;


import com.example.demo.models.Account;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;


    @PostMapping("/update/account")
    private int saveUser(@RequestBody Account account) {
        accountService.saveOrUpdate(account);
        return account.getId();
    }

}