package com.example.demo.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.demo.errors.exception.TheSameUsernameExeption;
import com.example.demo.errors.exception.UserNotFoundException;
import com.example.demo.helper.AuthorizationHelper;
import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {
     public static final String AUTHORIZATION= "Authorization";
     public static String BEARER = "Bearer ";


    @Autowired
    AuthorizationHelper authorizationhelper;

    @Autowired
    private AccountService accountService;


    @PostMapping("/account/save")
    private int saveUser(@RequestBody Account account) {
        try {
            accountService.save(account);
        }
        catch(Exception ex) {
            throw new TheSameUsernameExeption("Username must be unique");
        }
        return account.getId();
    }

    @GetMapping("/accounts")
    private List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    private Account getUserById(@PathVariable("id") int id) {
        Account account = null;
        try {
            account = accountService.getAccountById(id);
        } catch (Exception k) {
            throw new UserNotFoundException("Account: " + id + " not Found");
        }
        return account;
    }

    @DeleteMapping("/account/delete/{id}")
    private void deleteAccount(@PathVariable("id") int id) {
        try {
            accountService.delete(id);
        } catch (Exception k) {
            throw new UserNotFoundException("Account: " + id + " not Found");
        }
    }

    @PutMapping("/account/update/{id}")
    private Account putUser(@PathVariable("id") int id, @Valid @RequestBody Account accountDetails) {
      Account account = accountService.getAccountById(id);
      try {
        account.setToken(accountDetails.getToken());
        accountService.save(account);
  
      } catch (Exception k) {
        throw new UserNotFoundException("Account: " + id + " not Found");
      }
       
        return account;
    }

    //api za logout

   @PostMapping("/logout")
   public void logoutUser(@RequestHeader(AUTHORIZATION) String token) {
     authorizationhelper.authorize(token);
     try {
       
        accountService.delete(getAccountFromTable(token));

     } catch (Exception k) {
       k.printStackTrace();
       throw new UserNotFoundException("User not Found");
     }
      
   }

   public int getAccountFromTable(String token) {

    List<Account> accounts = accountService.getAllAccounts();
    for(int i=0; i<accounts.size(); i++) {
      if((BEARER + accounts.get(i).getToken()).equals(token))
       return accounts.get(i).getId();
    }
    return 0;
  }
}