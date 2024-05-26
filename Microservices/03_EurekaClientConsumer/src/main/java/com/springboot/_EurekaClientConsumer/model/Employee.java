package com.springboot._EurekaClientConsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int empId;
    private String empName;
    private double salary;
    private int age;
    private LocalDate empDob;
}
