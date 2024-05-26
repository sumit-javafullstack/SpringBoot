package com.springboot._EurekaClientConsumerWithLoadbalancer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.springboot._EurekaClientConsumerWithLoadbalancer.client.ConsumerClient;
import com.springboot._EurekaClientConsumerWithLoadbalancer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

  @Autowired
  ConsumerClient consumerClient;

  @GetMapping("/employees")
  public ResponseEntity<?> getEmployee() throws JsonProcessingException {
    List<Employee> employees = consumerClient.getEmployees();

    consumerClient.getEmployeesWithLoadBalance();
    if (employees.size() > 0) {
      return new ResponseEntity<>(employees, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Employees doesn't exit", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getEmployee(@PathVariable("id") int id) throws JsonProcessingException {

    Employee employee = consumerClient.getEmployebyid(id);

    if (employee != null) {
      return new ResponseEntity<>(employee, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(
          "Employee with the id " + id + " doesn't exist", HttpStatus.BAD_REQUEST);
    }
  }
}
