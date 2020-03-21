package com.example.demo.repositories;

import java.util.UUID;

import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID>  {
}

