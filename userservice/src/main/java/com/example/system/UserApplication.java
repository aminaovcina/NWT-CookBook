package com.example.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableAuthorizationServer
@EnableAutoConfiguration
@EnableDiscoveryClient
public class UserApplication {

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	public static void main(final String[] args) {
		SpringApplication.run(UserApplication.class, args);
		
	}

	@Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
	}
	
	
	/*@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}
	/*@RequestMapping(value = "/")
   public String home() {
      return "Eureka Client application";
   }
	@Bean
	 @LoadBalanced
	 public RestTemplate getRestTemplate() {
	 	return new RestTemplate();
	 }

	 //////prvobitno dodavanje usera i accounta kroz kod - prvi projektni zadatak
/*	@Bean
	public CommandLineRunner demo(UserRepository cRepository, AccountRepository aRepository){
		return (args) -> {
		/*	Calendar dateOfBirth = Calendar.getInstance();
			dateOfBirth.set(1997, 10, 3); //godina, mjesec, dan
			User user = new User("azra", "ibric",  Gender.female, dateOfBirth.getTime(), "sarajevo", "azra@bb.com");
			cRepository.save(user);

			Account account = new Account(user, "azraibric", "azra1234");
			accountRepository.save(account);

		};
	}*/
}
