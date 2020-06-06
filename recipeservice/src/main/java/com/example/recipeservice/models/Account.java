package com.example.recipeservice.models;

import java.util.List;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastname;

    public Account() {
        super();
    }
    public Account(String name, String lastname){
        this.name = name;
        this.lastname = lastname;
    }
    public Long getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getLastname(){
        return lastname;
    }

}