package com.example.demo.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.UserApplication;
import com.example.demo.controllers.UserController;
import com.example.demo.feign.SystemEvents;
import com.example.demo.grpc.EventServiceConsumer;
import com.example.demo.models.SystemEvent;
import com.example.demo.services.UserService;
import com.netflix.discovery.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CustomInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    EventServiceConsumer esc;
  
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        esc.trackEvent(new SystemEvent(response.getStatus(), 
        (new Date()).toString(), "userservice", request.getMethod()));
    }

    

}