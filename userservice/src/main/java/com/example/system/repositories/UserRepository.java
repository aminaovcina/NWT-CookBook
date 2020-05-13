package com.example.system.repositories;

import com.example.system.*;
import com.example.system.models.User;
import com.google.common.base.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
   
}

