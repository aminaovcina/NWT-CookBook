package com.example.system.errors.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class WrongPasswordException  extends RuntimeException {
  public WrongPasswordException(String message) {
    super(message);
  }
 
}