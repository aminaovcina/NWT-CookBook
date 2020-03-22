package com.example.recipeservice.controllers;

import com.example.recipeservice.repositories.RecipeCategoryInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("recipecategory")

@RestController
public class RecipeCategoryController {
    @Autowired
    private RecipeCategoryInterface recipeCategoryRepository;

}