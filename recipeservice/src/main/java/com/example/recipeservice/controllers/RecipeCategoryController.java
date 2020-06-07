package com.example.recipeservice.controllers;

import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.RecipeNotFoundException;
import com.example.recipeservice.helpers.AuthorizationHelper;
import com.example.recipeservice.repositories.RecipeCategoryInterface;
import com.example.recipeservice.services.RecipeCategoryService;
import com.example.recipeservice.services.UserHelperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.recipeservice.models.Category;
import com.example.recipeservice.models.Recipe;
import com.example.recipeservice.models.RecipeCategory;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class RecipeCategoryController {
    public static final String AUTHORIZATION= "Authorization";
    @Autowired
    private RecipeCategoryService recipeCategoryService;
    @Autowired
    UserHelperService userHelperService;
    @Autowired
    AuthorizationHelper authorizationhelper;
    @GetMapping("/recipe/category/{id}")
     private List<Category> getCategoryByRecipeId(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") Long id) {
        authorizationhelper.authorize(token);
        List<Category> kategorije = null;
        try{
            kategorije = recipeCategoryService.getCategoryByRecipeId(id);
         }catch(NoSuchElementException ex){
             throw new RecipeNotFoundException(id);
         }
         return kategorije;
     }
     @GetMapping("/recipes/category/{id}")
     private List<Recipe> getRecipesByCategoryId(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") Long id) {
        authorizationhelper.authorize(token);
        List<Recipe> kategorije = null;
        try{
            kategorije = recipeCategoryService.getRecipesByCategoryId(id);
         }catch(NoSuchElementException ex){
             throw new RecipeNotFoundException(id);
         }
         return kategorije;
     }
     @PostMapping("/recipe/category/save")
     private Long saveRecipeCategory(@RequestHeader(AUTHORIZATION) String token, @RequestBody RecipeCategory category) {
        authorizationhelper.authorize(token);
        recipeCategoryService.postRecipeCategory(category);
        return category.getId();
    } 



}