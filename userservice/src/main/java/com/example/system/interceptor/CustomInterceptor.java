package com.example.system.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.system.*;
import com.example.system.grpc.EventRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CustomInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    EventServiceConsumer consumer;

  
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                try {        
                    EventRequest er =  com.example.system.grpc.EventRequest.newBuilder()
                    .setStatus(response.getStatus())
                    .setRequest(request.getMethod())
                    .setServiceName("userservice")
                    .setTimestamp((new Date()).toString())
                    .build();
                    consumer.trackEvent(null, er, null);
                
                }
                catch(Exception e) {
                    System.out.println("OVDJEEEEEEEEEEEEEEEEEE"+ e);
                }
        return super.preHandle(request, response, handler);
    }


  

}