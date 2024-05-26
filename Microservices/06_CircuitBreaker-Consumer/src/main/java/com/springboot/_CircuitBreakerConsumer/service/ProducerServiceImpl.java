package com.springboot._CircuitBreakerConsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot._CircuitBreakerConsumer.client.CircuitBreakerConsumerClient;
import com.springboot._CircuitBreakerConsumer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {

  @Autowired private CircuitBreakerConsumerClient circuitBreakerConsumerClient;

  List<Employee> empList = new ArrayList<>();

  @Override
  public List<Employee> getEmployees() throws JsonProcessingException {
    ResponseEntity<String> employeeResponse = circuitBreakerConsumerClient.getEmployee();
    if (employeeResponse.getStatusCode().is2xxSuccessful() && employeeResponse.getBody() != null) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      empList =
          mapper.readValue(employeeResponse.getBody(), new TypeReference<List<Employee>>() {});
        return empList;
    }
    return empList;
  }
}
