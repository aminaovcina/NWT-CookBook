package com.example.system.server;

import com.example.system.EventServiceGrpc.EventServiceImplBase;
import com.example.system.EventServiceOuterClass.EventRequest;
import com.example.system.EventServiceOuterClass.EventResponse;
import io.grpc.stub.StreamObserver;


public class EventServiceImpl extends EventServiceImplBase {
  @Override
  public void trackEvent(EventRequest request, StreamObserver<EventResponse> responseObserver) {
    
    System.out.println("Request received from client:\n" + request);

    String greeting = new StringBuilder().append("Hello, ")
        .append(request.getTimestamp())
        .append(" ")
        .append(request.getStatus())
        .toString();

    EventResponse response = EventResponse.newBuilder()
        .setStatus(greeting)
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();

    super.trackEvent(request, responseObserver);
  }

 
  
    
}
 
   