// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/EventService.proto

package com.example.system.grpc;

public interface EventRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.example.system.grpc.EventRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string timestamp = 2;</code>
   * @return The timestamp.
   */
  java.lang.String getTimestamp();
  /**
   * <code>string timestamp = 2;</code>
   * @return The bytes for timestamp.
   */
  com.google.protobuf.ByteString
      getTimestampBytes();

  /**
   * <code>string serviceName = 3;</code>
   * @return The serviceName.
   */
  java.lang.String getServiceName();
  /**
   * <code>string serviceName = 3;</code>
   * @return The bytes for serviceName.
   */
  com.google.protobuf.ByteString
      getServiceNameBytes();

  /**
   * <code>string userId = 4;</code>
   * @return The userId.
   */
  java.lang.String getUserId();
  /**
   * <code>string userId = 4;</code>
   * @return The bytes for userId.
   */
  com.google.protobuf.ByteString
      getUserIdBytes();

  /**
   * <code>string request = 5;</code>
   * @return The request.
   */
  java.lang.String getRequest();
  /**
   * <code>string request = 5;</code>
   * @return The bytes for request.
   */
  com.google.protobuf.ByteString
      getRequestBytes();

  /**
   * <code>int32 status = 6;</code>
   * @return The status.
   */
  int getStatus();
}
