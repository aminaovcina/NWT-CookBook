package com.example.demo.models;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   

    @OneToOne(mappedBy = "user")
    private Account account;
    
    @NotNull
    @Size(max = 65)
    @Column(name = "firstName")
    private String firstName;

    @Size(max = 65)
    @Column(name = "lastName")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date date_Of_Birth;

    @Size(max = 100)
    private String city;

    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;


    public User() {
        super();
    }
    public User ( String firstName, String lastName, Gender gender, Date date_Of_Birth, String city, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.date_Of_Birth = date_Of_Birth;
        this.city = city;
        this.email = email;
    }
  //geter i i seteri

/**
 * @return the account
 */
public Account getAccount() {
    return account;
}/**
 * @return the city
 */
public String getCity() {
    return city;
}/**
 * @return the date_Of_Birth
 */
public Date getDate_Of_Birth() {
    return date_Of_Birth;
}/**
 * @return the email
 */
public String getEmail() {
    return email;
}/**
 * @return the firstName
 */
public String getFirstName() {
    return firstName;
}/**
 * @return the gender
 */
public Gender getGender() {
    return gender;
}/**
 * @return the id
 */
public int getId() {
    return id;
}/**
 * @return the lastName
 */
public String getLastName() {
    return lastName;
}/**
 * @param account the account to set
 */
public void setAccount(Account account) {
    this.account = account;
}/**
 * @param city the city to set
 */
public void setCity(String city) {
    this.city = city;
}/**
 * @param date_Of_Birth the date_Of_Birth to set
 */
public void setDate_Of_Birth(Date date_Of_Birth) {
    this.date_Of_Birth = date_Of_Birth;
}/**
 * @param email the email to set
 */
public void setEmail(String email) {
    this.email = email;
}/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
    this.firstName = firstName;
}/**
 * @param gender the gender to set
 */
public void setGender(Gender gender) {
    this.gender = gender;
}/**
 * @param id the id to set
 */
public void setId(int id) {
    this.id = id;
}/**
 * @param lastName the lastName to set
 */
public void setLastName(String lastName) {
    this.lastName = lastName;
}


}