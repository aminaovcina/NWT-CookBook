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
    public List<Recipe> getRecipesByCategoryId(Long id){
        List<Recipe> recepti = new ArrayList<Recipe>();
        List<RecipeCategory> svi = (List<RecipeCategory>) recipeCategoryRepository.findAll();
       for (RecipeCategory recipeCategory : svi) {
           Recipe recepat = recipeCategory.getRecipe();
           Category kategorija = recipeCategory.getCategory();
           if(kategorija.getId() == id){
               recepti.add(recepat);
           }       
        }
        return recepti;
    }
    public RecipeCategory postRecipeCategory(RecipeCategory recipeCategory){
        return recipeCategoryRepository.save(recipeCategory);
    }
    public void deleteFromRecipe(Long id){
        List<RecipeCategory> lista = (List<RecipeCategory>) recipeCategoryRepository.findAll();
        for(RecipeCategory a : lista){
            if(a.getRecipe().getId() == id) recipeCategoryRepository.delete(a);
        }
    }

}