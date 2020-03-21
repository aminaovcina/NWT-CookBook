package com.example.demo.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_", referencedColumnName = "id")
    private User user;

    private String username;
    private String password;


    public Account() {
        super();
    };
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
    public UUID getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }
    //getteri
    public String getPassword() {
        return password;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}