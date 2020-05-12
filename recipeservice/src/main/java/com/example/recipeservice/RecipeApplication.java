package com.example.recipeservice;

import java.util.Date;
import java.util.List;

import com.example.recipeservice.models.*;
import com.example.recipeservice.repositories.*;
import com.thoughtworks.xstream.mapper.Mapper.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class RecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeApplication.class, args);
	}
	// @Bean
	// public CommandLineRunner demo(CategoryInterface cRepository, AccountInterface aRepository, DishInterface dRepository, RecipeInterface rRepository,RecipeCategoryInterface rcRepositroy){
	// 	return (args) -> {
	// 		//insertovanje po jednog primjerka svih entiteta
	// 		//Dish dish = new Dish("Test dish", "The test star of the meal.");
	// 		//dRepository.save(dish);
	// 		// Dish dish2 = new Dish("Dessert", "Sweetest part of the meal");
	// 		// //dRepository.save(dish2);
	// 		//Category kategorija = new Category("Holiday");
	// 		//cRepository.save(kategorija);
	// 		// Category kategorija2 = new Category("NoBaking");
	// 		// cRepository.save(kategorija2);
	// 		//Date d1 = new Date(); 
	// 		//Account korisnik = new Account();
	// 		//aRepository.save(korisnik);
	// 		//Recipe recipe1 = new Recipe("Klepe", "Klepice slatke male", 30, 270, dish, d1, null, korisnik);
	// 		//rRepository.save(recipe1);
	// 		// rRepository.save(new Recipe("Tufahija", "Tufahije za pola sata", 30, 270, dish2, null, d1, korisnik));
			

	// 	};
	@RestController
  class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
      return this.discoveryClient.getInstances(applicationName);
    }
  }
	
}
