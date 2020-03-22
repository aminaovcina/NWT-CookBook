package com.example.recipeservice.repositories;

import java.util.UUID;

import com.example.recipeservice.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryInterface extends CrudRepository<Category, UUID>  {
}