package com.example.system.client;

import com.example.system.EventServiceGrpc;
import com.example.system.SystemEvent;
import com.example.system.EventServiceOuterClass.EventRequest;
import com.example.system.EventServiceOuterClass.EventResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
            .usePlaintext()
            .build();

        EventServiceGrpc.EventServiceBlockingStub stub
            = EventServiceGrpc.newBlockingStub(channel);


        EventResponse eventResponse = stub.trackEvent(EventRequest.newBuilder()
            .setTimestamp("Baeldung")
            .setStatus(200)
            .build());

        System.out.println("Response received from server:\n" + eventResponse);

        channel.shutdown();
    }
}