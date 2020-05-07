package com.example.demo.helper;

import java.util.List;

import com.example.demo.errors.exception.AuthorizationException;
import com.example.demo.models.Account;
import com.example.demo.models.User;
import com.example.demo.services.AccountService;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AuthorizationHelper {

    public static String BEARER = "Bearer ";

    @Autowired
    AccountService accountService;

    public  User authorize(String token) throws AuthorizationException {
        User user = getUserFromTable(token);

        if(user!=null) return user;
        else throw new AuthorizationException("You are not allowed to do this!!");
    }


    private  User getUserFromTable(String token) {

        List<Account> account = accountService.getAllAccounts();
        for(int i=0; i<account.size(); i++) {
          if((BEARER + account.get(i).getToken()).equals(token))
           return account.get(i).getUser();
        }
        return null;
      }
}