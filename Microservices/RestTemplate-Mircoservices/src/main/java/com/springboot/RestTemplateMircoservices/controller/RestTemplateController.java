package com.springboot.RestTemplateMircoservices.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.RestTemplateMircoservices.client.AsyncClient;
import com.springboot.RestTemplateMircoservices.client.EmployeeClient;
import com.springboot.RestTemplateMircoservices.model.Album;
import com.springboot.RestTemplateMircoservices.model.Employee;
import com.springboot.RestTemplateMircoservices.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest-template-microservice")
public class RestTemplateController {

  @Autowired  private EmployeeService employeeService;
  @Autowired private AsyncClient asyncClient;

  private static Map<Integer, Employee> map = new HashMap<>();
  List<Employee> empList = new ArrayList<>();

  static {
    map.put(101, new Employee(01, "Sumit", 1000.00, 25, LocalDate.now().minusYears(24)));
    map.put(102, new Employee(02, "Amit", 10000.00, 35, LocalDate.now().minusYears(44)));
    map.put(103, new Employee(03, "Suraj", 100000.00, 45, LocalDate.now().minusYears(64)));
  }


  @GetMapping("/employees")
  public ResponseEntity<?> getEmployee() throws JsonProcessingException {
    List<Employee> employees = employeeService.getEmployee();
    return new ResponseEntity<>(employees,HttpStatus.OK);
  }



  @GetMapping("/without-async")
  public ResponseEntity<?> getJsonwithoutAsync() throws JsonProcessingException {
    Collection<List<Album>> implWithoutAsync = asyncClient.getAlbumWithoutAsync();
    return new ResponseEntity<>(implWithoutAsync,HttpStatus.OK);
  }
}
