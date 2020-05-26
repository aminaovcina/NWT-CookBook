package com.example.recipeservice.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.CategoryNotFoundException;
import com.example.recipeservice.helpers.AuthorizationHelper;
import com.example.recipeservice.models.Category;
import com.example.recipeservice.services.CategoryService;
import com.example.recipeservice.services.UserHelperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    public static final String AUTHORIZATION= "Authorization";
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserHelperService userHelperService;
    @Autowired
    AuthorizationHelper authorizationhelper;

    @GetMapping("/category")
    private List<Category> getAllCategories(@RequestHeader(AUTHORIZATION) String token){
        authorizationhelper.authorize(token);
        return categoryService.getAllCategories();
    }
    @GetMapping("/category/{id}")
    private Category getCategoryById(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") Long id){
        authorizationhelper.authorize(token);
        Category category = null;
        try{
            category = categoryService.getCategoryById(id);
        }
       catch(NoSuchElementException ex){
        throw new CategoryNotFoundException(id);
       }
       return category;
    }
    @PostMapping("/category/save")
    private Long saveCategory(@RequestHeader(AUTHORIZATION) String token, @RequestBody Category category) {
        authorizationhelper.authorize(token);
        categoryService.createCategory(category);
        return category.getId();
    } 
}