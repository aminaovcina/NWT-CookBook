package com.example.recipeservice.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Size(max = 128)
    @Column(unique = true)
    private String title;

    @Size(max = 250)
    private String description;

    @OneToMany(mappedBy = "dish") //Jedan dish ima vise recipe
    @JsonIgnore
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
    public void setTitle(String title){
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
}