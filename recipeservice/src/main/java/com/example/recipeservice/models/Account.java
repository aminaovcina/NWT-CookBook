package com.example.recipeservice.models;

import java.util.List;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "account") //Jedan korisnik ima vise objava
    @JsonIgnore
    private List<Recipe> posts;
    public Account() {
        super();
    }
    public Long getId() {
        return id;
    }

}