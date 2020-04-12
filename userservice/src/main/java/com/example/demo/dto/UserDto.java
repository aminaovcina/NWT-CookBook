package com.example.demo.dto;


import java.util.List;

import com.example.demo.models.Recipe;
import com.example.demo.models.User;

public class UserDto extends User {

    private List<Recipe> recipes;
    private User user;

    public List<Recipe> getRecipes() {
        return recipes;
    }
    public User getUser() {
        return user;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setUser(User user) {
        this.user = user;
    }
}