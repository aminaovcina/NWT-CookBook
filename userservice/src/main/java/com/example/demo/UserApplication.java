package com.example.demo;

import java.util.Calendar;
import java.util.List;

import com.example.demo.controllers.UserController;
import com.example.demo.models.Account;
import com.example.demo.models.Gender;
import com.example.demo.models.User;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class UserApplication {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	public static void main(final String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@RequestMapping(value = "/")
   public String home() {
      return "Eureka Client application";
   }
	@Bean
	 @LoadBalanced
	 public RestTemplate getRestTemplate() {
	 	return new RestTemplate();
	 }

	@Bean
	public CommandLineRunner demo(UserRepository cRepository, AccountRepository aRepository){
		return (args) -> {
			Calendar dateOfBirth = Calendar.getInstance();
			dateOfBirth.set(1997, 10, 3); //godina, mjesec, dan
			User user = new User("azra", "ibric",  Gender.female, dateOfBirth.getTime(), "sarajevo", "azra@bb.com");
			cRepository.save(user);

			Account account = new Account(user, "azraibric", "azra1234");
			accountRepository.save(account);

		};
	}

	/*@RestController
	class ServiceInstanceRestController {

		@Autowired
		private DiscoveryClient discoveryClient;

		@RequestMapping("/service-instances/{applicationName}")
		public List<ServiceInstance> serviceInstancesByApplicationName(
				@PathVariable String applicationName) {
			return this.discoveryClient.getInstances(applicationName);
		}
	}

	/*public void run(final String... args) throws Exception {
		
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1997, 10, 3); //godina, mjesec, dan


		User user = new User("azra", "ibric",  Gender.female, dateOfBirth.getTime(), "sarajevo", "azra@bb.com");
		//User user1 = new User("ana", "anic",  Gender.female, dateOfBirth.getTime(), "sarajevo", "azrabb.com");
		
		userRepository.save(user);
		//userRepository.save(user1);

		Account account = new Account(user, "azraibric", "azra1234");
		//Account account1 = new Account(user, "anaanic", "ana1234");

		accountRepository.save(account);
		//accountRepository.save(account1);





	}*/
}
