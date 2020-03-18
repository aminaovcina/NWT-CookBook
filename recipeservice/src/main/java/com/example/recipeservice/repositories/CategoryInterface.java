package com.example.recipeservice.repositories;

import com.example.recipeservice.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryInterface extends CrudRepository<Category, Long>  {
}