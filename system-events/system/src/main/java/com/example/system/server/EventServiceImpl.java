package com.example.system.server;
import com.example.system.SystemEvent;
import com.example.system.SystemEventService;
import com.example.system.grpc.EventRequest;
import com.example.system.grpc.EventResponse;
import com.example.system.grpc.EventServiceGrpc;
import com.example.system.grpc.EventServiceGrpc.EventServiceImplBase;

import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.grpc.stub.StreamObserver;

@GRpcService
public class EventServiceImpl extends EventServiceGrpc.EventServiceImplBase {

  @Autowired
  SystemEventService service;

  @Override
  public void trackEvent(EventRequest request, StreamObserver<EventResponse> responseObserver) {
    service.save(new SystemEvent(request.getTimestamp(), request.getServiceName(), request.getRequest(), request.getStatus()));
  }

 
  
    
}
 
   