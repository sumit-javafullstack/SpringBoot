package com.lmbk.jcksn.practice.LmbkJcksnPropsFileAcess.controller;

import com.lmbk.jcksn.practice.LmbkJcksnPropsFileAcess.entity.Customer;
import com.lmbk.jcksn.practice.LmbkJcksnPropsFileAcess.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Example of lombok dependency
public class CustomerControllerClass {

    @GetMapping("/getCustomer")
    public String getCustomer(@RequestBody Customer customer){
    return  customer.getId()+customer.getCustName()+customer.getAge()+customer.getSalary();
    }

    @GetMapping("")
    public Employee getEmployee(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmployeeName("Sumit Kumar Mandal");
        employee.setSalary(1000.0);
        employee.setEmpCode('A');
        employee.setAddress("Bally, Howrah");
        employee.setTotalPf((double) (100*12));
        return employee;
    }
}
