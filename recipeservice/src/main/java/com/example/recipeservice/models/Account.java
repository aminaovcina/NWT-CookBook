package com.example.recipeservice.models;

import java.util.List;


import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "creator") //Jedan korisnik ima vise objava
    private List<Post> posts;
    public Account() {
        super();
    }
    @Override
    public String toString() {
        return String.format(
                "Account[id=%d]",
                id
        );
    }
    public Long getId() {
        return id;
    }

}