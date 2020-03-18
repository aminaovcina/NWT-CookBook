package com.example.demo.repositories;

import com.example.demo.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountInterface extends CrudRepository<User, Long>  {
}