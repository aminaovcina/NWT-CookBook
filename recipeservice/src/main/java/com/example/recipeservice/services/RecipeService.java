package com.example.recipeservice.services;

import java.util.ArrayList;
import java.util.List;

import com.example.recipeservice.models.Recipe;
import com.example.recipeservice.repositories.RecipeInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService{
    @Autowired
    RecipeInterface recipeRepository;
    public List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }
    public Recipe getRecipeById(Long id){
        return recipeRepository.findById(id).get();
    }
    public void saveOrUpdateRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }
    public void deleteRecipe(Long id){
        recipeRepository.deleteById(id);
    }
     public List<Recipe> getRecipesByDish(Long dish){
        List<Recipe> svi = (List<Recipe>) recipeRepository.findAll();
        List<Recipe> samo = new ArrayList<Recipe>();
         for(Recipe receptic : svi){
             Long novi = receptic.getDishId();
             if(novi == dish){
                 samo.add(receptic);
          }
      }
       return samo;
     }
}
