package com.example.demo.rabbitmqproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.demo.helper.AuthorizationHelper;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

import org.springframework.amqp.core.AmqpTemplate;

@RestController
@RequestMapping(value = "/userservice/")
public class RabbitMQWebController {    
    @Autowired
    private AmqpTemplate amqpTemplate;   

    @Autowired
    UserService userService;

    @Autowired
    AuthorizationHelper authorizationhelper;

    private UserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    
    
  @GetMapping("/users")
  public String getAllUsers(@RequestHeader("Authorization") String token) {
    authorizationhelper.authorize(token);
    amqpTemplate.convertAndSend("userserviceExchange", "userservice", userService.getAllUsers());
    return "Message sent to the RabbitMQ Successfully";
  }

}