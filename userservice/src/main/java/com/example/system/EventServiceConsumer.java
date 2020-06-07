package com.example.system;

import com.example.system.*;
import com.example.system.grpc.EventRequest;
import com.example.system.grpc.EventResponse;
import com.example.system.grpc.EventServiceGrpc;
import com.example.system.grpc.EventServiceGrpc.EventServiceBlockingStub;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

@Component
public class EventServiceConsumer{

    private EventServiceBlockingStub blockingStub;
    @Autowired
    private EurekaClient client;

    public void trackEvent(RpcController controller, EventRequest request, RpcCallback<EventResponse> done) {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("192.168.56.1", 6560)
        .usePlaintext()
                .build();
        blockingStub = EventServiceGrpc.newBlockingStub(channel);
        EventResponse response = blockingStub.trackEvent(EventRequest.newBuilder().setStatus(request.getStatus())
        .setRequest(request.getRequest())
        .setServiceName(request.getServiceName())
        .setTimestamp(request.getTimestamp())
        .setUserId(request.getUserId())
        .build());
        
    }
   

}