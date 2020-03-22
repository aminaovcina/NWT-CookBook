package com.example.recipeservice.models;

import javax.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;
    private int cookingtemperature;
    private int cookingtime;
    @OneToMany(mappedBy = "recipe") //Jedan recipe moze imati vise categorya
    private List<RecipeCategory> rc;
    @ManyToOne //vise recepata pripada istom tipu jela
    private Dish dish;
    @OneToOne(mappedBy = "recipe")
    private Post post;
    protected Recipe() {
        super();
    };
    //constructor
    public Recipe(String title, String description, int duration, int temperature){
        this.title = title;
        this.description = description;
        this.cookingtime = duration;
        this.cookingtemperature = temperature;
    }
    //getteri
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getCookingTime(){
        return cookingtime;
    }
    public int getCookingTemperature(){
        return cookingtemperature;
    }

}