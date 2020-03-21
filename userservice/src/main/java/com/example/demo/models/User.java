package com.example.demo.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
   

    @OneToOne(mappedBy = "user")
    private Account account;
    

    private String firstName;
    private String lastName;
    private String gender;
    private String date_Of_Birth;
    private String city;
    private String email;

    public User() {
        super();
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
    public String getFirstName() {
        return firstName;
    }
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    /**
     * @return the dateOfBirth
     */
   
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * @return the date_Of_Birth
     */
    public String getDate_Of_Birth() {
        return date_Of_Birth;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param date_Of_Birth the date_Of_Birth to set
     */
    public void setDate_Of_Birth(String date_Of_Birth) {
        this.date_Of_Birth = date_Of_Birth;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }/**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }/**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}