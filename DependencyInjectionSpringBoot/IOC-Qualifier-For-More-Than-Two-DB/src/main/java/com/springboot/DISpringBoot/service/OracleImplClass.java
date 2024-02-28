package com.springboot.DISpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class OracleImplClass implements OracleImpl{

    @Qualifier("OracleDB")
    @Autowired
    private DataSource dataSourceOracle;
    @Override
    public String testOracle() {
        return "ORACLE DATABASE";
    }
}
