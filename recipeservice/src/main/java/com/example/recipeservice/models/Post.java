package com.example.recipeservice.models;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private Date postdate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recipe", referencedColumnName = "id")
    private Recipe recipe;
    @ManyToOne //vise objava pripada istom korisniku
    private Account creator;
    protected Post() {
    };
    public String getTitle() {
        return title;
    }
    public Date getPostDate() {
        return postdate;
    }

}