package com.example.demo.controllers;


import java.util.List;
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

    @GetMapping("/accounts")
    private List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    private Account getUserById(@PathVariable("id") int id) {
        Account account = null;
        account = accountService.getAccountById(id);
        return account;
    }

    @DeleteMapping("/account/delete/{id}")
    private void deleteUser(@PathVariable("id") int id) {
        accountService.delete(id);
    }



}