package com.springboot.crud.features.SpringBootCRUD.model.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
  //spring-boot-starter-validation
  @Min(value = 5000,message = "Employee salary should be greater than 5000")
  @Column(name = "empSalary")
  private Double empSalary;
  //spring-boot-starter-validation

  @NotNull(message = "empDob can't be null")
  @Column(name = "empDob")
  private LocalDate empDob;

  //spring-boot-starter-validation

  @Column(name = "empStatus")
  @NotNull(message = "empStatus can't be null")
  private Boolean empStatus;

}
