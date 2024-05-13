package com.springboot.crud.features.SpringBootCRUD.service;


import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

     Employee saveEmployee(Employee employee);

     List<Employee> getEmployee();

     Employee updateEmployee(Employee employee);

      Page<Employee> getEmployeeUsingPage(Pageable pageable);
}
