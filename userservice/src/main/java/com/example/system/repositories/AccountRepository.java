package com.example.system.repositories;

import com.example.system.*;
import com.example.system.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>  {
    
    @Query("SELECT u FROM Account u WHERE u.user.id = ?1")
    Account findLoggedInUser(Integer user_id);
}