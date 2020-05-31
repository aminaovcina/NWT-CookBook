package com.example.recipeservice.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.recipeservice.exceptionHandling.DishNotFoundException;
import com.example.recipeservice.helpers.AuthorizationHelper;
import com.example.recipeservice.models.Dish;
import com.example.recipeservice.services.DishService;
import com.example.recipeservice.services.UserHelperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class DishController {
    public static final String AUTHORIZATION= "Authorization";
    @Autowired
    DishService dishService;
    @Autowired
    UserHelperService userHelperService;
    @Autowired
    AuthorizationHelper authorizationhelper;

    @GetMapping("/dish")
    private List<Dish> getAllDishes(@RequestHeader(AUTHORIZATION) String token){
        //authorizationhelper.authorize(token);
        return dishService.getAllDishes();
    }
    @GetMapping("/dish/{id}")
    private Dish getDishById(@RequestHeader(AUTHORIZATION) String token, @PathVariable("id") Long id){
        authorizationhelper.authorize(token);
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