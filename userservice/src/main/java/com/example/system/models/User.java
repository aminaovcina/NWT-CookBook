package com.example.system.models;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;

import java.util.Date;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    @Size(max = 65)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 65)
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;


    @Column(name = "Active")
    private Boolean active;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String token;

   // @Size(max = 100)
    //private String username;

    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;

    @JoinColumn(name="role")
    @ManyToOne(cascade=CascadeType.REFRESH)
    private Role role;

    public User() {
        super();
    }
    public User ( String firstName, String lastName, Gender gender, String date_Of_Birth, String city, String email, String token, Role role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = date_Of_Birth;
        this.city = city;
        this.email = email;
        this.token = token;
        this.role = role;
    }
    public User ( String firstName, String lastName, Gender gender, String date_Of_Birth, String city, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = date_Of_Birth;
        this.city = city;
        this.email = email;
    }

    @Override
    public String toString() {   
        return "Users [userName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
        }
  //geter i i seteri

  /**
   * @return the roleId
   */
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
 * @return the username
 */

  /**
   * @return the active
   */
  public Boolean getActive() {
      return active;
  }

  /**
   * @param active the active to set
   */
  public void setActive(Boolean active) {
      this.active = active;
  }
/**
 * @return the city
 */
public String getCity() {
    return city;
}/**
 * @return the date_Of_Birth
 */
public String getDate_Of_Birth() {
    return dateOfBirth;
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
 * @param city the city to set
 */
public void setCity(String city) {
    this.city = city;
}/**
 * @param date_Of_Birth the date_Of_Birth to set
 */
public void setDate_Of_Birth(String date_Of_Birth) {
    this.dateOfBirth = date_Of_Birth;
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
/**
 * @param token the token to set
 */
public void setToken(String token) {
    this.token = token;
}/**
 * @return the token
 */
public String getToken() {
    return token;
}

}