package com.example.system.models;

import java.util.Date;

public class UserRegister {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date dateOfBirth;
    private Boolean active;
    private String city;
    private String email;

    private String password;
    private String passwordConfirm;
    private Role role;

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }/**
     * @return the role
     */
    public Role getRole() {
        return role;
    }
    /**
     * @param username the username to set
     */


    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }/**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }/**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }/**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }/**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }/**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }/**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }/**
     * @param passwordConfirm the passwordConfirm to set
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }/**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }/**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }/**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }/**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }/**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }/**
     * @return the city
     */
    public String getCity() {
        return city;
    }/**
     * @return the password
     */
    public String getPassword() {
        return password;
    }/**
     * @return the passwordConfirm
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }/**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}