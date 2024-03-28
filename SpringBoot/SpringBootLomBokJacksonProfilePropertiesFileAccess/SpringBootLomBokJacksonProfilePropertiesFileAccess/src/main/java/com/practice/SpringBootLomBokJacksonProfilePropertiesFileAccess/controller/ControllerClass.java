package com.practice.SpringBootLomBokJacksonProfilePropertiesFileAccess.controller;

import com.practice.SpringBootLomBokJacksonProfilePropertiesFileAccess.entity.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/getCustomer")
    public String getCustomer(@RequestBody Customer customer){
    return  customer.getId()+customer.getCustName()+customer.getAge()+customer.getSalary();
    }
}
