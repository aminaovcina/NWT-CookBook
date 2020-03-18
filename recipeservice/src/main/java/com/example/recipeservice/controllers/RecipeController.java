package com.example.recipeservice.controllers;

import com.example.recipeservice.repositories.RecipeInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("recipe")

@RestController
public class RecipeController {
    @Autowired
    private RecipeInterface recipeRepository;

}