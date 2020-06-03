package com.example.recipeservice.services;

import java.util.ArrayList;
import java.util.List;

import com.example.recipeservice.models.Category;
import com.example.recipeservice.models.Recipe;
import com.example.recipeservice.models.RecipeCategory;
import com.example.recipeservice.repositories.RecipeCategoryInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeCategoryService {
    @Autowired
    RecipeCategoryInterface recipeCategoryRepository;
    public List<Category> getCategoryByRecipeId(Long id) {
        List<Category> kategorije = new ArrayList<Category>();
        List<RecipeCategory> svi = (List<RecipeCategory>) recipeCategoryRepository.findAll();
       for (RecipeCategory recipeCategory : svi) {
           Recipe recepat = recipeCategory.getRecipe();
           Category kategorija = recipeCategory.getCategory();
           if(recepat.getId() == id){
               kategorije.add(kategorija);
           }       
        }
        return kategorije;
    }
    public RecipeCategory postRecipeCategory(RecipeCategory recipeCategory){
        return recipeCategoryRepository.save(recipeCategory);
    }

}