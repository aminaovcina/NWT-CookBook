package com.example.recipeservice.controllers;

import com.example.recipeservice.repositories.CategoryInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("category")

@RestController
public class CategoryController {
    @Autowired
    private CategoryInterface categoryRepository;

}