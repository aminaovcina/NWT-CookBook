package com.example.recipeservice.models;

import java.util.UUID;

import javax.persistence.*;


@Entity
@Table(name = "RecipeCategory")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private Recipe recipe;
    @ManyToOne
    private Category category;
    protected RecipeCategory() {};
    public RecipeCategory(Recipe recipe, Category category){
        this.category = category;
        this.recipe = recipe;
    }
    public UUID getId(){
        return id;
    }
    public Recipe getRecipe(){
        return recipe;
    }
    public Category getCategory(){
        return category;
    }

}