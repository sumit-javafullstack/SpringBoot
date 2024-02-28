package com.springboot.DISpringBoot.repository;

import org.springframework.stereotype.Service;

//@Service, No need to declare as @Service as bean is already created using @Bean
// just need to inject(constructor/field/setter)
public class EmployeeDaoImpl implements EmployeeDao{

    @Override
    public String testEmployee() {
        return "SUMIT ";
    }


}
