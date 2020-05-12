package com.example.demo.errors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LOCKED)
public class DontHavePrivilegedException  extends RuntimeException {

  public DontHavePrivilegedException(String message) {
    super(message);
  }
 
}