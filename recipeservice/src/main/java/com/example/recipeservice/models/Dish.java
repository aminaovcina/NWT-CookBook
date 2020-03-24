package com.example.recipeservice.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String title;
    @Size(max = 250)
    private String description;
    @OneToMany(mappedBy = "dish") //Jedan dish ima vise recipe
    private List<Recipe> recipes;
    protected Dish() {
        super();
    };
    public Dish(String title, String description){
        this.title = title;
        this.description = description;
    }
    public Long getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}