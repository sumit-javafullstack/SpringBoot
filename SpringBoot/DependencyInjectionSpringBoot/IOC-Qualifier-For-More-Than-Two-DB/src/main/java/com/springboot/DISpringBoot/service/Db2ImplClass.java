package com.springboot.DISpringBoot.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
@AllArgsConstructor
@Service
public class Db2ImplClass implements Db2Impl{

    @Qualifier("db2DB")
    private final DataSource dataSourceDB2;
    @Override
    public String testDB2() {
        return "DB2 DATABASE";
    }
}
