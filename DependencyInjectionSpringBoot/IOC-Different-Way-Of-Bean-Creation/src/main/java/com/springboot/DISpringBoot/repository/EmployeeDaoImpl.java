package com.springboot.DISpringBoot.repository;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public String world(){
        return "World";
    }
}
