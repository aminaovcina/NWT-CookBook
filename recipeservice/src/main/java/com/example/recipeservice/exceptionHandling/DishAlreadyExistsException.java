package com.example.recipeservice.exceptionHandling;

public class DishAlreadyExistsException extends RuntimeException {
    public DishAlreadyExistsException(String dish) {
        super("Dish with name " + dish + " already exists.");
    }

}