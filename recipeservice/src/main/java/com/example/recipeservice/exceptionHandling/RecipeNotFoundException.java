package com.example.recipeservice.exceptionHandling;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(Long id) {
        super("Recipe with ID " + id + " does not exists.");
    }

}