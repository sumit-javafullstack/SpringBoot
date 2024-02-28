package com.springboot.DISpringBoot.repository;

import org.springframework.stereotype.Service;

@Service
public class StudentDaoImpl implements  StudentDao{
    @Override
    public String test() {
        return "Mandal";
    }
}
