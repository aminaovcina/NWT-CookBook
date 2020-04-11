package com.example.recipeservice.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.DishNotFoundException;
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
    @GetMapping("/dish/{id}")
    private Dish getDishById(@PathVariable("id") Long id){
        Dish category = null;
        try{
            category = dishService.getDishById(id);
        }
       catch(NoSuchElementException ex){
        throw new DishNotFoundException(id);
       }
       return category;
    }


}