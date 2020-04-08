package com.example.demo.dto;


import java.util.List;

import com.example.demo.models.Recipe;
import com.example.demo.models.User;

public class UserDto extends User {

    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}