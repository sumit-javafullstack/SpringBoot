package com.lmbk.jcksn.practice.LmbkJcksnPropsFileAcess.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Customer {

  private int id;
  private String custName;
  private Double salary;
  private int age;
}
