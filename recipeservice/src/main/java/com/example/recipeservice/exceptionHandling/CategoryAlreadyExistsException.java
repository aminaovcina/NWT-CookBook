package com.example.recipeservice.exceptionHandling;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String category) {
        super("Category with name " + category + " already exists.");
    }

}