package com.example.demo.rabitmqcustomer;

import com.example.demo.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {    
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);    
    
    @RabbitListener(queues = "userservice.queue")
    public void recievedMessage(User user) {
        logger.info("Recieved Message From RabbitMQ: " + user);
       
    }
}