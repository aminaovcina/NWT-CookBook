package com.example.recipeservice.services;

import java.util.List;

import com.example.recipeservice.exceptionHandling.CategoryAlreadyExistsException;
import com.example.recipeservice.models.Category;
import com.example.recipeservice.repositories.CategoryInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService{
    @Autowired
    CategoryInterface categoryRepository;
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).get();
    }
    
    public Category createCategory(Category name) {
        List<Category> categories = getAllCategories();
        categories.forEach(c-> {
            if (c.getName().equals(name.getName())) {
                throw new CategoryAlreadyExistsException(name.getName());
            }
        });
        return categoryRepository.save(name);
    }

}