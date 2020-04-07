/*package com.example.demo.feign;

import com.example.demo.models.Recipe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="recipeservice")
public interface RecipeClient {

    @GetMapping("/recipe")
    List<Recipe> getRecipes();
   
}*/