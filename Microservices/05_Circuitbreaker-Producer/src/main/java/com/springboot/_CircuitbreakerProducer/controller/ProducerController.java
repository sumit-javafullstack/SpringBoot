package com.springboot._CircuitbreakerProducer.controller;

import com.springboot._CircuitbreakerProducer.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/circuit-breaker-producer")
public class ProducerController {

    private boolean flag = false;
    private int count = 0;

    private static Map<Integer, Employee> map = new HashMap<>();
    static {
        map.put(101,new Employee(01,"Sumit",1000.00,25, LocalDate.now().minusYears(24)));
        map.put(102,new Employee(02,"Amit",10000.00,35, LocalDate.now().minusYears(44)));
        map.put(103,new Employee(03,"Suraj",100000.00,45, LocalDate.now().minusYears(64)));
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployee(){

        if(flag){
            count++;
            if(count%2==0){
                return new ResponseEntity<>("Something went wrong in circuit breaker- Source: Consumer", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(map.values(), HttpStatus.OK);
    }

    @GetMapping("/setFlag")
    public ResponseEntity<?> getEmployee(@RequestParam("flag") boolean flag){
        this.flag = flag;
    return new ResponseEntity<>("Sucesss",HttpStatus.OK);
    }

}
