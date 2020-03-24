package com.example.recipeservice.repositories;


import com.example.recipeservice.models.RecipeCategory;
import org.springframework.data.repository.CrudRepository;

public interface RecipeCategoryInterface extends CrudRepository<RecipeCategory, Long>  {
}