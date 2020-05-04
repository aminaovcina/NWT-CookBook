package com.example.system;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "events")
public class SystemEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    @Size(max = 65)
    @Column(name = "timestamp")
    private String timestamp;

    @Size(max = 65)
    @Column(name = "service_name")
    private String serviceName;

    @Size(max = 65)
    @Column(name = "user_id")
    private String userId;

    @Size(max = 100)
    @Column(name = "request")
    private String request;

    @Size(max = 100)
    @Column(name = "status")
    private String status;


    public SystemEvent() {
        super();
    }
    public SystemEvent ( String timestamp, String serviceName, String userId, String request, String status) {

        this.timestamp = timestamp;
        this.serviceName = serviceName;
        this.userId = userId;
        this.request = request;
        this.status = status;
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
    public String getStatus() {
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
    }/**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }/**
     * @param request the request to set
     */
    public void setRequest(String request) {
        this.request = request;
    }/**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }/**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }/**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }/**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}