package com.example.demo.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.grpc.EventServiceConsumer;
import com.example.demo.models.SystemEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CustomInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    EventServiceConsumer esc;
  
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        try {        
            esc.trackEvent(new SystemEvent(response.getStatus(), 
            (new Date()).toString(), "userservice", request.getMethod()));
        
        }
        catch(Exception e) {
            System.out.println("OVDJEEEEEEEEEEEEEEEEEE"+ e);
        }
    }

    

}