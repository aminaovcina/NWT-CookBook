package com.example.recipeservice.models;

import javax.persistence.*;


@Entity
@Table(name = "recipecategory")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    protected RecipeCategory() {
        super();
    };
    public RecipeCategory(Recipe recipe, Category category){
        this.category = category;
        this.recipe = recipe;
    }
    public Long getId(){
        return id;
    }
    public Recipe getRecipe(){
        return recipe;
    }
    public Category getCategory(){
        return category;
    }

}