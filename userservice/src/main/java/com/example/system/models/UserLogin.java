package com.example.system.models;

public class UserLogin {
    private String username;
    private String password;
   /**
    * @param username the username to set
    */
   public void setUsername(String username) {
       this.username = username;
   }/**
    * @param password the password to set
    */
   public void setPassword(String password) {
       this.password = password;
   }/**
    * @return the username
    */
   public String getUsername() {
       return username;
   }/**
    * @return the password
    */
   public String getPassword() {
       return password;
   }
}