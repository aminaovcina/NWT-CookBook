package com.example.system.rabbit;

import com.example.system.SystemEvent;
import com.example.system.helper.ApplicationConfigReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
/**
 * Message Listener for RabbitMQ
 */
@Service
public class MessageListener {
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    private final RabbitTemplate rabbitTemplate;
    private ApplicationConfigReader applicationConfig;
    private RabbitSender messageSender;
    @Autowired
    ApplicationConfigReader applicationConfigReader;

    public ApplicationConfigReader getApplicationConfig() {
        return applicationConfig;
    }
    @Autowired
    public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public RabbitSender getMessageSender() {
		return messageSender;
	}

	@Autowired
	public void setMessageSender(RabbitSender messageSender) {
		this.messageSender = messageSender;
    }
    
    @Autowired
    public MessageListener(final RabbitTemplate rabbitTemplate) {
      this.rabbitTemplate = rabbitTemplate;
  }

    /**
     * Message listener for app2
     * 
     */
    @RabbitListener(queues = "${app2.queue.name}")
    public void receiveMessageForApp2(String reqObj) {
        log.info("Received message: {} from app2 queue.", reqObj);
        String exchange = getApplicationConfig().getApp1Exchange();
        String routingKey = getApplicationConfig().getApp1RoutingKey();
    /* Sending to Message Queue */
    try {
      //Write object...
      messageSender.sendMessage(rabbitTemplate, exchange, routingKey, "success");
    } catch (Exception ex) {
      messageSender.sendMessage(rabbitTemplate, exchange, routingKey, "error");
    }
    }
}