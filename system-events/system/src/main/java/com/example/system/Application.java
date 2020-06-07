package com.example.system;
import java.io.IOException;

import com.example.system.server.EventServiceImpl;

import java.io.IOException;

import com.example.system.helper.ApplicationConfigReader;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableRabbit
public class Application  implements RabbitListenerConfigurer {

    @Autowired
	private ApplicationConfigReader applicationConfig;
        
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
       
	public ApplicationConfigReader getApplicationConfig() {
		return applicationConfig;
	}
	public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	/* This bean is to read the properties file configs */
	@Bean
	public ApplicationConfigReader applicationConfig() {
	return new ApplicationConfigReader();
	}

	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getApp2Exchange() {
	return new TopicExchange(getApplicationConfig().getApp2Exchange());
	}
	/* Creating a bean for the Message queue */
	@Bean
	public Queue getApp2Queue() {
	return new Queue(getApplicationConfig().getApp2Queue());
	}
	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingApp2() {
	return BindingBuilder.bind(getApp2Queue()).to(getApp2Exchange()).with(getApplicationConfig().getApp2RoutingKey());
	}
	

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}


	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}


	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}
       

 }
