package com.example.demo;

import java.util.UUID;

import com.example.demo.models.Account;
import com.example.demo.models.User;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User user = new User();
		user.setId(UUID.randomUUID());
		user.setFirstName("azra");
		user.setLastName("ibric");
		user.setEmail("ayra@bstelecom.ba");
		user.setCity("sarajevo");
		user.setDate_Of_Birth("12.2.2012.");
		user.setGender("zensko");

		
		userRepository.save(user);

		Account account = new Account();
		account.setId(UUID.randomUUID());
		account.setPassword("1234azra");
		account.setUsername("azra");
		account.setUser(user);

		
		accountRepository.save(account);
		
	} 

	
}
