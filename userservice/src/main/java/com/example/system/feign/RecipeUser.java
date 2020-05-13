package com.example.system.feign;

import com.example.system.models.Recipe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="recipeservice")
public interface RecipeUser{

    @GetMapping("/recipes/user/{id}")
    List<Recipe> getRecipesByUser(@PathVariable("id") Long id);
  
}