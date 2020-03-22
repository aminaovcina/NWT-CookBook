package com.example.recipeservice.models;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "Dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "dish") //Jedan dish ima vise recipe
    private List<Recipe> recipes;
    protected Dish() {
    };
    public Dish(String title, String description, List<Recipe> recipes){
        this.title = title;
        this.description = description;
        this.recipes = recipes;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}