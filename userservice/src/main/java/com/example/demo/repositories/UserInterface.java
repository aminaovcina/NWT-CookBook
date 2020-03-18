package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserInterface extends CrudRepository<User, Long>  {
}