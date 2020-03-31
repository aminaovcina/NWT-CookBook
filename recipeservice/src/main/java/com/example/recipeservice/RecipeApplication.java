package com.example.recipeservice;

import java.util.List;

import com.example.recipeservice.models.*;
import com.example.recipeservice.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@EnableEurekaClient
@SpringBootApplication
public class RecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeApplication.class, args);
	}
	// @Bean
	// @LoadBalanced
	// public RestTemplate getRestTemplate() {
	// 	return new RestTemplate();
	// }
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

// @RestController
// class ServiceInstanceRestController {

// 	@Autowired
// 	private DiscoveryClient discoveryClient;

// 	@RequestMapping("/service-instances/{applicationName}")
// 	public List<ServiceInstance> serviceInstancesByApplicationName(
// 			@PathVariable String applicationName) {
// 		return this.discoveryClient.getInstances(applicationName);
// 	}
// }