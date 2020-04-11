package com.example.recipeservice.exceptionHandling;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id) {
        super("Category with ID " + id + " does not exists.");
    }
}