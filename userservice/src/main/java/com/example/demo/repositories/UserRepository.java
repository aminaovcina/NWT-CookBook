package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.models.UserLogin;
import com.google.common.base.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
    UserLogin findByUsername(String username);


}

