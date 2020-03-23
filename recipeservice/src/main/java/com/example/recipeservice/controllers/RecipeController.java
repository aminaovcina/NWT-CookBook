package com.example.recipeservice.controllers;

import java.util.List;
import java.util.UUID;

import com.example.recipeservice.models.Recipe;
import com.example.recipeservice.services.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/recipe")
    private List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }
    @GetMapping("/recipesByDish/{id}")
    private List<Recipe> getRecipesByDish(@PathVariable("id") Long id){
        return recipeService.getRecipesByDish(id);
    }
    @GetMapping("/recipe/{id}")
    private Recipe getRecipeById(@PathVariable("id") Long id) {
        return recipeService.getRecipeById(id);
    }
    @DeleteMapping("/recipe/{id}")
    private void deleteRecipe(@PathVariable("id") Long id) {
        recipeService.deleteRecipe(id);
    }
    @PostMapping("/recipe/save")
    private Long saveUser(@RequestBody Recipe recipe) {
        recipeService.saveOrUpdateRecipe(recipe);
        return recipe.getId();
    }

}