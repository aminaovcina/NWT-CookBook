package com.example.demo.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
   
    @Autowired
    AccountRepository ar;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        List<Account> accounts = ar.findAll();
        Account account = getAccountWithUsername(accounts, username);
         
         if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            final UserDetails principal = new User(username, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
            return auth;
  
           
        } else {
            return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(org.springframework.security.authentication.UsernamePasswordAuthenticationToken.class);
    }

    public Account getAccountWithUsername( List<Account> accounts, String username){
        Account account = new Account();
        for(int i=0; i<accounts.size(); i++) {
            if(accounts.get(i).getUsername().equals(username)) {
                account = accounts.get(i);
            }
        }
        return account;
    }
 }