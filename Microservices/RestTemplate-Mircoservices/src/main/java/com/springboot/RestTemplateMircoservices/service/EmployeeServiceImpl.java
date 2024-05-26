package com.springboot.RestTemplateMircoservices.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.RestTemplateMircoservices.client.EmployeeClient;
import com.springboot.RestTemplateMircoservices.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements  EmployeeService{

    @Autowired
    private EmployeeClient employeeClient;
    @Override
    public List<Employee> getEmployee() throws JsonProcessingException {
        List<Employee> employee = employeeClient.getEmployee();
        return employee;
    }
}
