package com.lmbk.jcksn.practice.LmbkJcksnPropsFileAcess.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.employee.info")
//@PropertySource("classpath:MyProperties.yml")
@Data
//@Component
public class AppProperties {

    private String name;
    private int age;
    private List<String> hobbies;
    private Education education;
    private List<Publications> publications;

}
