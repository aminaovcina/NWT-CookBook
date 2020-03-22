package com.example.recipeservice.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToMany(mappedBy = "creator") //Jedan korisnik ima vise objava
    private List<Post> posts;
    public Account() {}
    @Override
    public String toString() {
        return String.format(
                "Account[id=%d]",
                id
        );
    }
    public UUID getId() {
        return id;
    }

}