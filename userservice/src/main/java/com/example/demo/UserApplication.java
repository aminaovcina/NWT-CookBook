package com.example.demo;

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

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthorizationServer
@ComponentScan
public class UserApplication {

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	public static void main(final String[] args) {
		SpringApplication.run(UserApplication.class, args);
		/*final InstanceInfo instanceInfo = client.getNextServerFromEureka("systemevents", false);
        final ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort())
                .usePlaintext()
                .build();

        final EventServiceGrpc.EventServiceBlockingStub stub = EventServiceGrpc.newBlockingStub(channel); 
        stub.trackEvent(EventRequest.newBuilder()
        .setTimestamp("")
        .setStatus(200)
        .setRequest(null)
        .setServiceName(null)
        .build());

        channel.shutdown();*/
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
