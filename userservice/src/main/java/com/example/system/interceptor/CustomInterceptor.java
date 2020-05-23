package com.example.system.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.system.*;
import com.example.system.errors.exception.AuthorizationException;
import com.example.system.grpc.EventRequest;
import com.example.system.helper.AuthorizationHelper;
import com.example.system.models.User;
import com.example.system.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CustomInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    EventServiceConsumer consumer;

    @Autowired
    AuthorizationHelper authorization;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
                try {       
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    String userId = null;
                    try {
                        User user = authorization.authorize(request.getHeader("Authorization"));
                        userId = String.valueOf(user.getId());
                    } catch(AuthorizationException e) {
                        userId = null;
                    }
                    EventRequest er =  com.example.system.grpc.EventRequest.newBuilder()
                    .setStatus(response.getStatus())
                    .setRequest(request.getMethod())
                    .setServiceName("userservice")
                    .setTimestamp(format.format(new Date()))
                    .setUserId(userId)
                    .build();
                    consumer.trackEvent(null, er, null);
                
                }
                catch(Exception e) {
                    System.out.println("OVDJEEEEEEEEEEEEEEEEEE"+ e);
                }
        super.afterCompletion(request, response, handler, ex);
    }

}