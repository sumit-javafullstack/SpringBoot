package com.springboot.crud.features.SpringBootCRUD.model.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "empId")
  private int empId;

  @Column(name = "empName")
  private String empName;

  @Column(name = "empSalary")
  private Double empSalary;

  @Column(name = "empDob")
  private LocalDate empDob;

  @Column(name = "empStatus")
  private boolean empStatus;
}
