package com.example.recipeservice.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(max = 65)
    private String title;
    @Temporal(TemporalType.DATE)
    private Date postdate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_recipe", referencedColumnName = "id")
    private Recipe recipe;
    @ManyToOne //vise objava pripada istom korisniku
    private Account creator;
    protected Post() {
        super();
    };
    public String getTitle() {
        return title;
    }
    public Date getPostDate() {
        return postdate;
    }

}