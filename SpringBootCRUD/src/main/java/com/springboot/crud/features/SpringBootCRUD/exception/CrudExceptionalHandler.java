package com.springboot.crud.features.SpringBootCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CrudExceptionalHandler {

  @ExceptionHandler(EmployeeException.class)
  public ResponseEntity<ErrorMessage> exception(EmployeeException e) {

    ErrorMessage message = new ErrorMessage(e.getMessage(), LocalDateTime.now(), e.getErrorCode());

    return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {

    Map<String,String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage= error.getDefaultMessage();
      errors.put(fieldName,errorMessage);
    });
    return new ResponseEntity<>(errors,HttpStatus.BAD_GATEWAY);
  }
}
