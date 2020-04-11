package com.example.recipeservice.controllers;

import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.RecipeNotFoundException;
import com.example.recipeservice.repositories.RecipeCategoryInterface;
import com.example.recipeservice.services.RecipeCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.recipeservice.models.Category;
import com.example.recipeservice.models.RecipeCategory;

import java.util.List;


@RestController
public class RecipeCategoryController {
    @Autowired
    private RecipeCategoryService recipeCategoryService;

    @GetMapping("/recipe/category/{id}")
     private List<Category> getCategoryByRecipeId(@PathVariable("id") Long id) {
        List<Category> kategorije = null;
        try{
            kategorije = recipeCategoryService.getCategoryByRecipeId(id);
         }catch(NoSuchElementException ex){
             throw new RecipeNotFoundException(id);
         }
         return kategorije;
     }



}