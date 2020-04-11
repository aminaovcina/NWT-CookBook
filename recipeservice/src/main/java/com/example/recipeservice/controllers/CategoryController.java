package com.example.recipeservice.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.CategoryNotFoundException;
import com.example.recipeservice.models.Category;
import com.example.recipeservice.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    private List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/category/{id}")
    private Category getCategoryById(@PathVariable("id") Long id){
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
    private Long saveCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return category.getId();
    } 
}