package com.example.system.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Size(max = 200)
    private String token;
    
    public Account() {
        super();
    };
  
    public Account(User user, String token) {
        this.user = user;
        this.token = token;
    }
    //constructor

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }/**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
   
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    //getteri
   
    /**
     * @param password the password to set
     */
    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }/**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}