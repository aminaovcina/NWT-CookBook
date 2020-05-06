package com.example.demo.models;

import java.util.Set;

public class SystemEvent {
    private int id;
    private String timestamp;
    private String serviceName;
    private String userId;
    private String request;
    private int status;

    public SystemEvent(int status, String timestamp, String serviceName, String request) {
        this.status = status;
        this.timestamp = timestamp;
        this.serviceName = serviceName;
        this.request = request;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }/**
     * @return the request
     */
    public String getRequest() {
        return request;
    }/**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }/**
     * @return the status
     */
    public int getStatus() {
        return status;
    }/**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }/**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
}