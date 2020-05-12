package com.example.demo.errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.errors.exception.AuthenticationException;
import com.example.demo.errors.exception.AuthorizationException;
import com.example.demo.errors.exception.DontHavePrivilegedException;
import com.example.demo.errors.exception.TheSameEmailException;
import com.example.demo.errors.exception.TheSameUsernameExeption;
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


  //error za pogresan tip --- /user/kk
  @ExceptionHandler({MethodArgumentTypeMismatchException.class })
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
    MethodArgumentTypeMismatchException ex, WebRequest request) {

      String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
      ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getName() +" is in incorrect format!", error);

      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  //error za /user/id get i user/delete/id delete- nepostojeci id ---/user/89 ili /user/delete/89
  @ExceptionHandler({UserNotFoundException.class })
  public ResponseEntity<Object> handleUserNotFound(
    UserNotFoundException ex, WebRequest request) {
      ApiError apiError =  new ApiError(HttpStatus.NOT_FOUND, "ID is not existing!", ex.getMessage());
      
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  //error za pogresnu metodu - umjesto delete, get
  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {

      StringBuilder builder = new StringBuilder();
      builder.append(ex.getMethod() + " method is not supported for this request. Supported methods are ");
      ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
    
      ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  //error 500
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handle500Error(Exception ex, HttpServletRequest request, HttpServletResponse response) {
      if (ex instanceof NullPointerException) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
     
      ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong on our side! Try again later...", ex.getMessage());
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  //error za duplu email adresu 
  @ExceptionHandler({TheSameEmailException.class })
  public ResponseEntity<Object> handleTheSameEmail(
    TheSameEmailException ex, WebRequest request) {
      ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST,  "E-mail is not unique!", ex.getMessage());
      
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  //error za dupli username
  @ExceptionHandler({TheSameUsernameExeption.class })
  public ResponseEntity<Object> handleTheUsername(
    TheSameUsernameExeption ex, WebRequest request) {
      ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST,  "Username is not unique!", ex.getMessage());
      
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

//za autorizaciju
  @ExceptionHandler({AuthorizationException.class })
  public ResponseEntity<Object> handleAuthorization(
    AuthorizationException ex, WebRequest request) {
      ApiError apiError =  new ApiError(HttpStatus.UNAUTHORIZED,  "Authorization error!", ex.getMessage());
      
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }
//za autentifikaciju
  @ExceptionHandler({AuthenticationException.class })
  public ResponseEntity<Object> handleAuthentication(
    AuthenticationException ex, WebRequest request) {
      ApiError apiError =  new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "Authentification error!", ex.getMessage());
      
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }


  //za pogresnu rolu
  @ExceptionHandler({DontHavePrivilegedException.class })
  public ResponseEntity<Object> handlePrivilaged(
    DontHavePrivilegedException ex, WebRequest request) {
      ApiError apiError =  new ApiError(HttpStatus.LOCKED, "You don't have privilaged!", ex.getMessage());
      
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }
}