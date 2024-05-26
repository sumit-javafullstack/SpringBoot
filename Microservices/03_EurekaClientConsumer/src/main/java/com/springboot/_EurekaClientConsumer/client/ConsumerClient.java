package com.springboot._EurekaClientConsumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot._EurekaClientConsumer.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ConsumerClient {

  RestTemplate restTemplate = new RestTemplate();

  @Autowired DiscoveryClient discoveryClient;
  ObjectMapper mapper = new ObjectMapper();

  public String connectWithProducer() {
    mapper.registerModule(new JavaTimeModule());
    List<ServiceInstance> instances = discoveryClient.getInstances("cst-employee-producer");
    ServiceInstance serviceInstance = instances.get(0);
    String baseUrl = serviceInstance.getUri().toString();

    return baseUrl;
  }

  public List<Employee> getEmployees() throws JsonProcessingException {
    List<Employee> empList = new ArrayList<>();
    String url = connectWithProducer() + "/producer/employees";
    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    if (response.getBody() != null) {
      empList =
              mapper.readValue(response.getBody(), new TypeReference<List<Employee>>() {});
      return empList;
    }

    return null;
  }

  public Employee getEmployebyid(int id) throws JsonProcessingException {

    Employee employee = null;
    String url = connectWithProducer();
    String finalUrl =
        UriComponentsBuilder.fromHttpUrl(url).path("/producer/{id}").buildAndExpand(id).toUriString();
    ResponseEntity<String> response =
        restTemplate.exchange(finalUrl, HttpMethod.GET, null, String.class);
    if (response.getBody() != null) {
      employee = mapper.readValue(response.getBody(), new TypeReference<Employee>() {});
      return employee;
    }
    return null;
  }
}
