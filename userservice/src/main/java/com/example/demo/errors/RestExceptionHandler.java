package com.example.demo.errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.errors.exception.UserNotFoundException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


  //error za pogresan tip
  @ExceptionHandler({MethodArgumentTypeMismatchException.class })
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
    MethodArgumentTypeMismatchException ex, WebRequest request) {
      String error = 
        ex.getName() + " should be of type " + ex.getRequiredType().getName();
      
        ApiError apiError = 
        new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  //error za /user/id get i user/delete/id delete- nepostojeci id 
  @ExceptionHandler({UserNotFoundException.class })
  public ResponseEntity<Object> handleUserNotFound(
    UserNotFoundException ex, WebRequest request) {
        ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }


  //error za pogresnu metodu - umjesto delete, get
  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {

      StringBuilder builder = new StringBuilder();
      builder.append(ex.getMethod());
      builder.append( " method is not supported for this request. Supported methods are ");
      ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
    
      ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

//error za istu email adresu u postu za user i account
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleTheSameEmail(Exception ex, HttpServletRequest request, HttpServletResponse response) {
      if (ex instanceof NullPointerException) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
     
      ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "The same e-mail address", ex.getMessage());
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }
}