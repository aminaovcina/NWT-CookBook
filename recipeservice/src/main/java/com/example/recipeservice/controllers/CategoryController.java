package com.example.recipeservice.controllers;

import java.util.List;

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

}