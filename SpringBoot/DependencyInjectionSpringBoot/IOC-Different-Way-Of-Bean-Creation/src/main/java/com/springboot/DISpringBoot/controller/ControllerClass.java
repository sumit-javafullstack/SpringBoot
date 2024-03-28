package com.springboot.DISpringBoot.controller;

import com.springboot.DISpringBoot.repository.EmployeeDao;
import com.springboot.DISpringBoot.service.EmployeeService;
import com.springboot.DISpringBoot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

    /* ****Field Injection****
   1) Dependencies are declared as private instance variables with the @ Autowired annotation.
   2) The Spring framework uses reflection to directly set the field values.
   3) Field injection can simplify code and reduce verbosity, but it may hinder
      testability and make it harder to detect missing dependencies.
     */
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    StudentService studentService;
//    private final EmployeeService employeeService;
//    public ControllerClass(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }

    @GetMapping("/test")
    public String test(){
        return employeeService.hello();
    }

    @GetMapping("/testStudent")
    public String testStudent(){
        return studentService.test();
    }
}
