package com.example.demo.models;

public class User {
    public int id;
    public String firstName;
    public String lastName;

    public User(){
    }

    public User(int id,String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer setId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String firstName) {
        this.lastName = firstName;
    }
}


    
    
    
