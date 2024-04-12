package com.lmbk.jcksn.practice.LmbkJcksnPropsFileAcess.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmbk.jcksn.practice.LmbkJcksnPropsFileAcess.entity.Customer;
import com.lmbk.jcksn.practice.LmbkJcksnPropsFileAcess.entity.Employee;
import com.lmbk.jcksn.practice.LmbkJcksnPropsFileAcess.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Jackson dependency
public class EmployeeControllerClass {

  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired
  AppProperties appProperties;

  /*
    two endpoints ->
               /serialize and
               /deserialize
    This process in springboot occur automatically as springboot has uses jackson dependency.
  */
  // converting json to java object
  @GetMapping("/serialize")
  public String serialize() throws JsonProcessingException {
    Employee employee = new Employee();
    employee.setId(1);
    employee.setEmployeeName("Sumit Kumar Mandal");
    employee.setSalary(1000.0);
    employee.setEmpCode('A');
    employee.setAddress("Bally, Howrah");
    employee.setTotalPf((double) (100 * 12));
    return mapper.writeValueAsString(employee);
  }
  // converting java to json object
  @GetMapping("/deserialize")
  public void deserialize() throws JsonProcessingException {
    String jsonInput =
        "{\n"
            + "    \"id\": \"34\",\n"
            + "    \"custName\": \"Sumit Mandal\",\n"
            + "    \"salary\": \"1000.00\",\n"
            + "    \"age\": \"56\"\n"
            + "}";
    Customer customer = mapper.readValue(jsonInput, Customer.class);
    System.out.println(customer.toString());
  }

  @PostMapping("/employee")
  public Employee getEmployee(@RequestBody Employee employee) {
    System.out.println(appProperties.getName());
    System.out.println(appProperties.getEducation());
    System.out.println(appProperties.getHobbies());
    System.out.println(appProperties.getPublications());
    return employee;
  }
}
