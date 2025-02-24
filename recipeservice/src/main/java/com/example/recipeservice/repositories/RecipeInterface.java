package com.example.recipeservice.repositories;

import com.example.recipeservice.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeInterface extends CrudRepository<Recipe, Long>  {
}