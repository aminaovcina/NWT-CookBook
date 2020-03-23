package com.example.recipeservice.repositories;

import java.util.UUID;

import com.example.recipeservice.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountInterface extends CrudRepository<Account, Long>  {
}