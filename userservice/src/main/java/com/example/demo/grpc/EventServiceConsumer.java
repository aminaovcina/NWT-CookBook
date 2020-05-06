package com.example.demo.grpc;

import com.example.demo.EventServiceGrpc;
import com.example.demo.EventServiceOuterClass.EventRequest;
import com.example.demo.feign.SystemEvents;
import com.example.demo.models.SystemEvent;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@EnableEurekaClient
@EnableDiscoveryClient
@Component
public class EventServiceConsumer {
    @Autowired
    private EurekaClient client;

    public void trackEvent(SystemEvent event) {
        final InstanceInfo instanceInfo = client.getNextServerFromEureka("systemevents", false);
        final ManagedChannel channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort())
                .usePlaintext()
                .build();

        final EventServiceGrpc.EventServiceFutureStub stub = EventServiceGrpc.newFutureStub(channel); 
        stub.trackEvent(EventRequest.newBuilder()
        .setTimestamp(event.getTimestamp())
        .setStatus(event.getStatus())
        .setRequest(event.getRequest())
        .setServiceName(event.getServiceName())
        .setStatus(event.getStatus())
        .build());

        channel.shutdown();

    }
}