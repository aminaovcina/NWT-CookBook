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
			Dish dish = new Dish("Main dish", "The star of the meal.");
			dRepository.save(dish);
			Dish dish2 = new Dish("Dessert", "Sweetest part of the meal");
			dRepository.save(dish2);
			Category kategorija = new Category("EasyMeal");
			cRepository.save(kategorija);
			Category kategorija2 = new Category("NoBaking");
			cRepository.save(kategorija2);
			rRepository.save(new Recipe("Klepe", "Klepice slatke male", 30, 270, dish, null));
			rRepository.save(new Recipe("Tufahija", "Tufahije za pola sata", 30, 270, dish2, null));

		};
	}
}
