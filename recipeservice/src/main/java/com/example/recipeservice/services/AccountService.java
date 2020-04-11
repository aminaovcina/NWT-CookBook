package com.example.recipeservice.services;

import com.example.recipeservice.models.Account;
import com.example.recipeservice.repositories.AccountInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountInterface accountRepository;

    public Account getAccountById(Long id){
        return accountRepository.findById(id).get();
    }
}