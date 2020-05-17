package com.example.system.port;

import com.example.system.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IUserPublisher {
    void sendToOrderCallback(User user) throws JsonProcessingException;
}