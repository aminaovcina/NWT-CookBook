package com.example.recipeservice.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;


@Entity
@Table(name = "Recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(max = 128)
    private String title;
    @Size(max = 300)
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
    public Recipe(String title, String description, int duration, int temperature, Dish dish, List<RecipeCategory> rc){
        this.title = title;
        this.description = description;
        this.cookingtime = duration;
        this.cookingtemperature = temperature;
        this.dish = dish;
        this.rc = rc;
    }
    //getteri
    public Long getId(){
        return id;
    }
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
    public Dish getDish(){
        return dish;
    }
    public Post getPost(){
        return post;
    }

}