package com.example.recipeservice.controllers;

import com.example.recipeservice.repositories.DishInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("dish")

@RestController
public class DishController {
    @Autowired
    private DishInterface dishRepository;

}