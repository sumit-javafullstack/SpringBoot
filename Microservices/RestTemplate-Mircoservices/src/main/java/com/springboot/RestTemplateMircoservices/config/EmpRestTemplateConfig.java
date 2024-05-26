package com.springboot.RestTemplateMircoservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmpRestTemplateConfig {

  @Bean(name = "restTemplateEmpService")
  public RestTemplate restTemplate() {
    var factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(40000);
    factory.setReadTimeout(40000);
    return new RestTemplate(factory);
  }


}
