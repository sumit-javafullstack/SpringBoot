package com.springboot.RestTemplateMircoservices.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.RestTemplateMircoservices.config.CustomResponseErrorHandler;
import com.springboot.RestTemplateMircoservices.exception.EmployeeException;
import com.springboot.RestTemplateMircoservices.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class EmployeeClient {
  String url = "http://localhost:7776/circuit-breaker-producer/employees";
  @Autowired
  @Qualifier("restTemplateEmpService")
  private RestTemplate restTemplate;
  List<Employee> empList = new ArrayList<>();

  ObjectMapper mapper = new ObjectMapper();
  ResponseEntity<String> response;



  public List<Employee> getEmployee() throws JsonProcessingException {
    restTemplate.setErrorHandler(new CustomResponseErrorHandler("Service Name: circuit-breaker-producer: "));
    mapper.registerModule(new JavaTimeModule());
    response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
      empList = mapper.readValue(response.getBody(), new TypeReference<List<Employee>>() {});

      return empList;
    }
    return null;
  }
}
