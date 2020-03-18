package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String city;
    private String email;


    protected User() {
    };
    //constructor

    //getteri
    public String getFistName() {
        return firstName;
    }
}