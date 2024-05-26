package com.springboot._CircuitBreakerConsumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot._CircuitBreakerConsumer.exception.EmployeeException;
import com.springboot._CircuitBreakerConsumer.model.Employee;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CircuitBreakerConsumerClient {
  String url = "http://localhost:7776/circuit-breaker-producer/employees";
  String serviceName = "http://localhost:7776/circuit-breaker-producer/employees";
  ObjectMapper mapper = new ObjectMapper();

  @Qualifier("restTemplateEmpService")
  @Autowired
  RestTemplate restTemplate;

  List<Employee> empList = new ArrayList<>();
  ResponseEntity<String> response;

  @CircuitBreaker(name = "jasKeyCompute", fallbackMethod = "subscribeFallBackMethod")
  public ResponseEntity<String> getEmployee() throws JsonProcessingException {
    mapper.registerModule(new JavaTimeModule());
    response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    // all the other error like
    // 1. RestClientException
    // 2.HttpServerErrorException etc will go to fallback as this error indicates something
    // wrong with server

    return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
  }

  public ResponseEntity<String> subscribeFallBackMethod(
      HttpServerErrorException httpServerErrorException) throws IOException {
    int statusCode = httpServerErrorException.getStatusCode().value();
    log.info("\t Producer app responded with InternalServerError, generating cached Response");
    if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
      throw new EmployeeException(serviceName + " Internal server error", statusCode);
    } else {
      // For other error codes, throw a generic exception
      throw new EmployeeException("Received HTTP error: " + statusCode);
    }
  }

  public ResponseEntity<String> subscribeFallBackMethod(
      HttpClientErrorException httpClientException) {
    log.info("\t Producer app responded with HttpClientErrorException, generating cached Response");
    int statusCode = httpClientException.getStatusCode().value();

    if (statusCode == HttpStatus.NOT_FOUND.value()) {
      throw new EmployeeException(serviceName + " Resource not found", statusCode);
    } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
      throw new EmployeeException(serviceName + " Invalid request", statusCode);
    } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
      throw new EmployeeException(serviceName + " Unauthorized access", statusCode);
    } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
      throw new EmployeeException(serviceName + " Access forbidden", statusCode);
    } else {
      // For other error codes, throw a generic exception
      throw new EmployeeException("Received HTTP error: " + statusCode);
    }
  }

  public ResponseEntity<String> subscribeFallBackMethod(
      CallNotPermittedException callNotPermittedException) {
    log.info("\t Circuit is in open state, generating cached Response");
    return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
  }

  public ResponseEntity<String> subscribeFallBackMethod(
          Exception callNotPermittedException) {
    log.info("\t Circuit is in open state, generating cached Response");
    throw new EmployeeException("Un-expected error occur " );
  }
}
