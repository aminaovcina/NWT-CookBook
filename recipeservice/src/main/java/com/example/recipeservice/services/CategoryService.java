package com.example.recipeservice.services;

import java.util.List;

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
}