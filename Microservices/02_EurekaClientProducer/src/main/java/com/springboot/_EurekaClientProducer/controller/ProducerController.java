package com.springboot._EurekaClientProducer.controller;

import com.springboot._EurekaClientProducer.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private static Map<Integer, Employee> map = new HashMap<>();
    static {
        map.put(101,new Employee(01,"Sumit",1000.00,25, LocalDate.now().minusYears(24)));
        map.put(102,new Employee(02,"Amit",10000.00,35, LocalDate.now().minusYears(44)));
        map.put(103,new Employee(03,"Suraj",100000.00,45, LocalDate.now().minusYears(64)));

//        map.put(101,new Employee(01,"Sumit",1000.00,25));
//        map.put(102,new Employee(02,"Amit",10000.00,35));
//        map.put(103,new Employee(03,"Suraj",100000.00,45));

    }

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployee(){

        if(map.size() > 0){
            return new ResponseEntity<>(map.values(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Employees doesn't exit",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") int id){
    Employee emp = map.get(id);
    if(emp != null){
        return new ResponseEntity<>(emp,HttpStatus.OK);
    } else{
        return new ResponseEntity<>("Employee with the id "+ id +" doesn't exist",HttpStatus.BAD_REQUEST);
    }

    }

}
