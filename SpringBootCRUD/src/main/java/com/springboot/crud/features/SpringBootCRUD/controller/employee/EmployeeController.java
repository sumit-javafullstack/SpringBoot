package com.springboot.crud.features.SpringBootCRUD.controller.employee;


import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;
import com.springboot.crud.features.SpringBootCRUD.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeService) {
        this.employeeService = employeService;
    }

    @PostMapping("/save-employee")
    public Employee saveEmployee(@RequestBody Employee employee){

        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/get-employee")
    public List<Employee> getEmployee(){

        return employeeService.getEmployee();
    }

}
