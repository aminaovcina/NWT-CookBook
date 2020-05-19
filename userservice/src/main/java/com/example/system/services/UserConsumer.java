package com.example.system.services;

import java.io.IOException;

import com.example.system.models.User;
import com.example.system.port.IUserMessging;
import com.example.system.port.IUserPublisher;
import com.example.system.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class UserConsumer  {
/*
    @Autowired
    private IUserPublisher userPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void consumeMessage(String content) {

        try {
            User userString = objectMapper.readValue(content, User.class);
            userRepository.save(userString);
            userPublisher.sendToOrderCallback(userString);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    */
}