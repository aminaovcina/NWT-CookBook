package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Account")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    private String password;


    protected Account() {
    };
    //constructor

    //getteri
    public String getPassword() {
        return password;
    }
}