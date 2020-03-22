package com.example.recipeservice;

import com.example.recipeservice.models.*;
import com.example.recipeservice.repositories.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(CategoryInterface cRepository, AccountInterface aRepository, DishInterface dRepository, PostInterface pRepository, RecipeInterface rRepository,RecipeCategoryInterface rcRepositroy){
		return (args) -> {
			dRepository.save(new Dish("Main dish", "The star of the meal.",null));
		};
	}
}
