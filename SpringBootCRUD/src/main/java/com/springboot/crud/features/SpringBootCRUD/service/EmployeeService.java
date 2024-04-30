package com.springboot.crud.features.SpringBootCRUD.service;


import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;

import java.util.List;

public interface EmployeeService {

     Employee saveEmployee(Employee employee);

     List<Employee> getEmployee();
}
