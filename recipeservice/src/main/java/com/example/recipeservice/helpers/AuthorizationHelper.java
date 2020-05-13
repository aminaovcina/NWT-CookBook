package com.example.recipeservice.helpers;

import com.example.recipeservice.exceptionHandling.AuthorizationException;
import com.example.recipeservice.services.UserHelperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationHelper {
    @Autowired
    UserHelperService userHelperService;
    public boolean authorize(String token) throws AuthorizationException {
        boolean validan = userHelperService.getAccountValidate(token);
        if(validan == true) return true;
        else throw new AuthorizationException("You are not logged in!");
    }

    public boolean authorizeRole(String token) throws AuthorizationException {
        int rola = userHelperService.getRoleByToken(token);
        if(rola == 1) return true;
        else throw new AuthorizationException("Your are not allowed to do this!");
    }

}