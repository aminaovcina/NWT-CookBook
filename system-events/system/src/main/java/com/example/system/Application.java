package com.example.system;
import java.io.IOException;

import com.example.system.server.EventServiceImpl;

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
public class Application {
        
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
       

 }
