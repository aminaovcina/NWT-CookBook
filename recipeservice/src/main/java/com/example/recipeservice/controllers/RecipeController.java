package com.example.recipeservice.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.AccountNotFoundException;
import com.example.recipeservice.exceptionHandling.DishNotFoundException;
import com.example.recipeservice.exceptionHandling.RecipeNotFoundException;
import com.example.recipeservice.helpers.UserDeseralizer;
import com.example.recipeservice.models.Recipe;
import com.example.recipeservice.services.AccountService;
import com.example.recipeservice.services.DishService;
import com.example.recipeservice.services.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    DishService dishService;
    AccountService accountService;

    @GetMapping("/recipe")
    private List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }
    @GetMapping("/recipesByDish/{id}")
    private List<Recipe> getRecipesByDish(@PathVariable("id") Long id){
        List<Recipe> recepti = null;
        try{
            recepti = recipeService.getRecipesByDish(id);
        }
        catch(NoSuchElementException ex){
            throw new DishNotFoundException(id);
        }  
        return recepti; 
    }
    @GetMapping("/recipes/user/{id}")
    private List<Recipe> getRecipesByUser(@PathVariable("id") Long id){
        List<Recipe> recepti = null;
        try{
            recepti = recipeService.getRecipesByUser(id);
        }
        catch(NoSuchElementException ex){
            throw new AccountNotFoundException(id);
        }  
        return recepti;   
    }
    @GetMapping("/recipe/{id}")
    private Recipe getRecipeById(@PathVariable("id") Long id) {
        Recipe recipe = null;
        try{
            recipe = recipeService.getRecipeById(id);
        }catch(NoSuchElementException ex){
            throw new RecipeNotFoundException(id);
        }
        return recipe;
    }
    @DeleteMapping("/recipe/delete/{id}")
    private void deleteRecipe(@PathVariable("id") Long id) {
        try{
            recipeService.deleteRecipe(id);
        }catch(Exception ex){
            throw new RecipeNotFoundException(id);
        }      
    }
    @PostMapping("/recipe/save")
    private Long saveRecipe(@RequestBody Recipe recipe) {
        recipeService.saveOrUpdateRecipe(recipe);
        return recipe.getId();
    } 
    
   

}