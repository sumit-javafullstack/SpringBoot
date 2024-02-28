package com.springboot.DISpringBoot.service;

import com.springboot.DISpringBoot.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    /* ***Setter Injection***
    1) These setters are used to inject the dependencies into the class.

    2) Setter injection allows for optional dependencies and the ability
        to change dependencies at runtime.

     */
    private StudentDao studentDao;

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public String test(){
        return  "SUMIT "+ studentDao.test();
    }
}
