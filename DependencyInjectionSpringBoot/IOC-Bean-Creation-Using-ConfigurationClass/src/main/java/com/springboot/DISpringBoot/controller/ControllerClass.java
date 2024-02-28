package com.springboot.DISpringBoot.controller;

import com.springboot.DISpringBoot.client.CustomerUtility;
import com.springboot.DISpringBoot.client.Model;
import com.springboot.DISpringBoot.repository.EmployeeDao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ControllerClass {

    private EmployeeDao employeeDao;
    private  CustomerUtility customerUtility;


    @GetMapping("/testemployee")
    public ResponseEntity<List<Model>> getEmployee(){
        String str = employeeDao.testEmployee() + "Mandal";
        return customerUtility.testRestGetTemplate();

//        return customerUtility.testRestPostTemplate();


    }
}
