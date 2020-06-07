package com.example.system.rabbit;

import com.example.system.helper.ApplicationConfigReader;
import com.example.system.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
/**
 * Message Listener for RabbitMQ
 */
@Service
public class MessageListener {
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);
    @Autowired
    ApplicationConfigReader applicationConfigReader;
    /**
     * Message listener for app1
     * @param UserDetails a user defined object used for deserialization of message
     */
    @RabbitListener(queues = "${app1.queue.name}")
    public void receiveMessageForApp1(final String data) {
        log.info("Received message: {} from app1 queue.", data);
        if(data.equals("error")) {
            //deleteObject()
        } 
    }
    /**
     * Message listener for app2
     * 
     */

}