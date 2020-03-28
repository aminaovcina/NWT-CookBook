package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @NotNull
    @Size(max = 128)
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(max = 128)
    private String password;


    public Account() {
        super();
    };

    public Account(User user, String username, String password) {
        this.user = user;
        this.username = username;
        this.password = password;
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