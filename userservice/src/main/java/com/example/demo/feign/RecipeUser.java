package com.example.demo.feign;

import com.example.demo.models.Recipe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="recipeservice")
public interface RecipeUser{

    @GetMapping("/recipes/user/{id}")
    List<Recipe> getRecipesByUser(@PathVariable("id") Long id);

  
}