package com.example.demo.grpc;

import com.example.demo.feign.SystemEvents;
import com.example.demo.models.SystemEvent;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class EventServiceConsumer {
   
    public void trackEvent(SystemEvent event) {
        //try{
            //se.save(event);

        
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("192.168.56.1", 6565)
                .usePlaintext()
                .build();

        final EventServiceGrpc.EventServiceBlockingStub stub = EventServiceGrpc.newBlockingStub(channel); 
        EventResponse response = stub.trackEvent(EventRequest.newBuilder()
        .setTimestamp(event.getTimestamp())
        .setStatus(event.getStatus())
        .setRequest(event.getRequest())
        .setServiceName(event.getServiceName())
        .build());
  /*  }
    catch(Exception e) {
       e.printStackTrace();
    }*/

    }
}