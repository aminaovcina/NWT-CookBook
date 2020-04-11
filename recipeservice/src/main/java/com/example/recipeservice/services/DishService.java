package com.example.recipeservice.services;

import java.util.List;

import com.example.recipeservice.models.Dish;
import com.example.recipeservice.repositories.DishInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishService{
    @Autowired
    DishInterface dishRepository;
    public List<Dish> getAllDishes() {
        return (List<Dish>) dishRepository.findAll();
    }
    public Dish getDishById(Long id){
        return dishRepository.findById(id).get();
    }
}