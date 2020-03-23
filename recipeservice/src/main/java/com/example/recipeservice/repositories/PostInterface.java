package com.example.recipeservice.repositories;

import java.util.UUID;

import com.example.recipeservice.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostInterface extends CrudRepository<Post, Long>  {
}