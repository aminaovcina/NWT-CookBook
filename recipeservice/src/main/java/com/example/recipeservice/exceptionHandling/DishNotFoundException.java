package com.example.recipeservice.exceptionHandling;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(Long id) {
        super("Dish with ID " + id + " does not exists.");
    }
}