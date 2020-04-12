package com.example.demo.errors.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TheSameEmailException  extends RuntimeException {
  public TheSameEmailException(String message) {
    super(message);
  }
}