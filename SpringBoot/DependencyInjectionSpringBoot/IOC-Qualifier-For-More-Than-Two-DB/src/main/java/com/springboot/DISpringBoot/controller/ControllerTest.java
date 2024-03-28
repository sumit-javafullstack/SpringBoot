package com.springboot.DISpringBoot.controller;

import com.springboot.DISpringBoot.service.Db2Impl;
import com.springboot.DISpringBoot.service.OracleImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ControllerTest {

private final Db2Impl db2;
private final OracleImpl oracle;

    @GetMapping("test")
    public String test(){
        String str1=db2.testDB2();
        String str2 = oracle.testOracle();
        return str1 +" "+str2;
}

}
