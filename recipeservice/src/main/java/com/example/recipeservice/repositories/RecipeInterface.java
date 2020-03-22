package com.example.recipeservice.repositories;

import java.util.UUID;

import com.example.recipeservice.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeInterface extends CrudRepository<Recipe, UUID>  {
}