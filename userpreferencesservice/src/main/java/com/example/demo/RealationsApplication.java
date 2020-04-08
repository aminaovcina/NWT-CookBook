package com.example.demo;

import com.example.demo.models.Favorites;
import com.example.demo.models.Subscription;
import com.example.demo.repositories.FavoritesRepository;
import com.example.demo.repositories.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class RealationsApplication {

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	FavoritesRepository favoritesRepository;

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(RealationsApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner run(SubscriptionRepository subscriptionRepository, FavoritesRepository favoritesRepository) throws Exception {
        return (String[] args) -> {/*
            Subscription sub1 = new Subscription(9, 10);
			Subscription sub2 = new Subscription(11, 12);
			Favorites fav1 = new Favorites(19, 13);
			Favorites fav2 = new Favorites(15, 53);
            subscriptionRepository.save(sub1);
			subscriptionRepository.save(sub2);
			favoritesRepository.save(fav1);
			favoritesRepository.save(fav2);
			subscriptionRepository.findAll().forEach(System.out::println);
			favoritesRepository.findAll().forEach(System.out::println); */
        };
    }
/*
	@Component
	public class ApplicationRunnerBean implements ApplicationRunner {
    	@Override
    	public void run(ApplicationArguments arg0) throws Exception {
			  System.out.println("ApplicationRunnerBean");
			  subscriptionRepository.save(new Subscription(1, 5, 3));
			  Iterable<Subscription> iterator = subscriptionRepository.findAll();
			  System.out.println("All expense items: ");
        	  iterator.forEach(item -> System.out.println(item));
	}
}*/

}
