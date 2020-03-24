package com.example.recipeservice.repositories;


import com.example.recipeservice.models.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishInterface extends CrudRepository<Dish, Long>  {
}