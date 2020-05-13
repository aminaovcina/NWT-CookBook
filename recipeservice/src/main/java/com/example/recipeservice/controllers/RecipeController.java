package com.example.recipeservice.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.AccountNotFoundException;
import com.example.recipeservice.exceptionHandling.DishNotFoundException;
import com.example.recipeservice.exceptionHandling.RecipeNotFoundException;
import com.example.recipeservice.helpers.AuthorizationHelper;
import com.example.recipeservice.helpers.UserDeseralizer;
import com.example.recipeservice.models.Recipe;
import com.example.recipeservice.services.AccountService;
import com.example.recipeservice.services.DishService;
import com.example.recipeservice.services.RecipeService;
import com.example.recipeservice.services.UserHelperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {
    public static final String AUTHORIZATION= "Authorization";
    @Autowired
    RecipeService recipeService;
    DishService dishService;
    AccountService accountService;
    @Autowired
    UserHelperService userHelperService;
    @Autowired
    AuthorizationHelper authorizationhelper;

    @GetMapping("/recipe")
    private List<Recipe> getAllRecipes(@RequestHeader(AUTHORIZATION) String token){
        authorizationhelper.authorize(token);
        return recipeService.getAllRecipes();
    }
    @GetMapping("/recipesByDish/{id}")
        private List<Recipe> getRecipesByDish(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") Long id){
        authorizationhelper.authorize(token);
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
    private List<Recipe> getRecipesByUser(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") Long id){
        authorizationhelper.authorize(token);
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
    private Recipe getRecipeById(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") Long id) {
        authorizationhelper.authorize(token);
        authorizationhelper.authorizeRole(token);
        Recipe recipe = null;
        try{
            recipe = recipeService.getRecipeById(id);
        }catch(NoSuchElementException ex){
            throw new RecipeNotFoundException(id);
        }
        return recipe;
    }
    @DeleteMapping("/recipe/delete/{id}")
    private void deleteRecipe(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") Long id) {
        authorizationhelper.authorize(token);
        try{
            recipeService.deleteRecipe(id);
        }catch(Exception ex){
            throw new RecipeNotFoundException(id);
        }      
    }
    @PostMapping("/recipe/save")
    private Long saveRecipe(@RequestHeader(AUTHORIZATION) String token, @RequestBody Recipe recipe) {
        authorizationhelper.authorize(token);
        recipeService.saveOrUpdateRecipe(recipe);
        return recipe.getId();
    } 
    
   

}