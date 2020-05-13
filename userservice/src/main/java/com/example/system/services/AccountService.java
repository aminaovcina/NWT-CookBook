package com.example.system.services;

import java.util.List;

import com.example.system.*;
import com.example.system.models.Account;
import com.example.system.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;


    public void save(Account account) {
        accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(int id) {
        return accountRepository.findById(id).get();
    }

    public void delete(int id) {
        accountRepository.deleteById(id);
    }

}