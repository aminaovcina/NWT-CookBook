package com.example.recipeservice.controllers;

import java.util.List;

import com.example.recipeservice.models.Dish;
import com.example.recipeservice.services.DishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DishController {
    @Autowired
    DishService dishService;

    @GetMapping("/dish")
    private List<Dish> getAllDishes(){
        return dishService.getAllDishes();
    }


}