package com.example.system.services;

import java.util.List;

import com.example.system.*;
import com.example.system.helper.ApplicationConfigReader;
import com.example.system.models.User;
import com.example.system.rabbit.RabbitSender;
import com.example.system.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public void findOne(int id) {
        userRepository.deleteById(id);
    }
}