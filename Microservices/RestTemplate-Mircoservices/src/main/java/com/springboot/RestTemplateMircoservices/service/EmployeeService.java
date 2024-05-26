package com.springboot.RestTemplateMircoservices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.RestTemplateMircoservices.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployee() throws JsonProcessingException;
}
