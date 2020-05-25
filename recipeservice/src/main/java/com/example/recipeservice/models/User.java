package com.example.recipeservice.models;

public class User {
    private int id;
    private String firstName;
    private String lastName;
   // private Gender gender;
    private String dateOfBirth;
    private Boolean active;
    private String city;
    private String token;
    private String email;
    private Role role;


    public String getToken() {
        return token;
    }
}