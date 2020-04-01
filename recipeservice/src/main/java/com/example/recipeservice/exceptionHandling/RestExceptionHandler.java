package com.example.recipeservice.exceptionHandling;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

  @ExceptionHandler({MethodArgumentTypeMismatchException.class })
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
    MethodArgumentTypeMismatchException ex, WebRequest request) {
      String error = 
        ex.getName() + " should be of type " + ex.getRequiredType().getName();
      
        ApiError apiError = 
        new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }
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
  @ExceptionHandler({RecipeNotFoundException.class })
  public ResponseEntity<Object> handleRecipeNotFound(
    RecipeNotFoundException ex, WebRequest request) {
        ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getMessage());
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleTheSameProperty(Exception ex, HttpServletRequest request, HttpServletResponse response) {
      if (ex instanceof NullPointerException) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
     
      ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "The same property already exists", ex.getMessage());
      return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }
}