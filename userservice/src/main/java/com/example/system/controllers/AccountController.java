package com.example.system.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.system.errors.exception.DontHavePrivilegedException;
import com.example.system.errors.exception.TheSameUsernameExeption;
import com.example.system.errors.exception.UserNotFoundException;
import com.example.system.helper.AuthorizationHelper;
import com.example.system.models.Account;
import com.example.system.models.User;
import com.example.system.repositories.AccountRepository;
import com.example.system.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class AccountController {
     public static final String AUTHORIZATION= "Authorization";
     public static String BEARER = "Bearer ";


    @Autowired
    AuthorizationHelper authorizationhelper;

    @Autowired
    private AccountService accountService;


    @PostMapping("/account/save")
    public int saveUser(@RequestHeader(AUTHORIZATION) String token, @RequestBody Account account) {
        authorizationhelper.authorize(token);
        try {
            accountService.save(account);
        }
        catch(Exception ex) {
            throw new TheSameUsernameExeption("Username must be unique");
        }
        return account.getId();
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(@RequestHeader(AUTHORIZATION) String token) {
        authorizationhelper.authorize(token);
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/{id}")
    public Account getUserById(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") int id) {
        authorizationhelper.authorize(token);
        Account account = null;
        try {
            account = accountService.getAccountById(id);
        } catch (Exception k) {
            throw new UserNotFoundException("Account: " + id + " not Found");
        }
        return account;
    }

    @DeleteMapping("/account/delete/{id}")
    public void deleteAccount(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") int id) {
        authorizationhelper.authorize(token);

        Account account = accountService.getAccountById(id);
        User user = account.getUser();

        //provjera da li je user privilegovan


        if(user.getRole().getRoleId()==1) {
            accountService.delete(id);
        } 
        else throw new DontHavePrivilegedException(user.getEmail() + " is not privilaged");
    }

    @PutMapping("/account/update/{id}")
    public Account putUser(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") int id, @Valid @RequestBody Account accountDetails) {
        authorizationhelper.authorize(token);

        //provjera da li je user privilegovan

      Account account = accountService.getAccountById(id);
        User user = account.getUser();


      if(user.getRole().getRoleId()==1) {
        account.setToken(accountDetails.getToken());
        accountService.save(account);
  
        } 
        else throw new DontHavePrivilegedException(user.getEmail() + " is not privilaged");
        
        return account;
    }

    //api za validaciju sA mikroservisima
    @GetMapping("/accounts/validate")
    public boolean getAccountValidate(@RequestHeader(AUTHORIZATION) String token) {
        
        try {

            //provjera da li token ima u tabeli accounta
            List<Account> accounts = accountService.getAllAccounts();
            for(int i=0; i<accounts.size(); i++)
                if((BEARER + accounts.get(i).getToken()).equals(token)) return true;


        } catch (Exception k) {
            throw new UserNotFoundException("Token: " + token + " not Found");
        }
        return false;
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