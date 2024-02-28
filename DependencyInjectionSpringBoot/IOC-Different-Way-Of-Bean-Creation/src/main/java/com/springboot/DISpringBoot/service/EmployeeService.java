package com.springboot.DISpringBoot.service;

import com.springboot.DISpringBoot.repository.EmployeeDao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    // Constructor Injection
    /*
    1) Constructor injection ensures that the required dependencies are provided
       at the time of object creation.
    2) Once instantiated, the class holds references to these dependencies.
    3) It promotes immutability, as dependencies can be declared as final.
    4) We can also use lombok dependency to create constructor
       @AllArgsConstructor
       @NoArgsConstructor
     */

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    public String hello(){
        return  "Hello " + employeeDao.world();
    }
}
