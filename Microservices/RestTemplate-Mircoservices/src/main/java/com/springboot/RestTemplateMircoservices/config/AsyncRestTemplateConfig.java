package com.springboot.RestTemplateMircoservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AsyncRestTemplateConfig {

  @Bean(name = "restTemplateAsyncCallService")
  public RestTemplate restTemplate(ResponseErrorHandler errorHandler) {
    var factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(300000);
    factory.setReadTimeout(300000);
    RestTemplate restTemplate = new RestTemplate(factory);
    restTemplate.setErrorHandler(errorHandler);
    return restTemplate;
  }

  @Bean
  public ResponseErrorHandler errorHandler() {
    return new CustomResponseErrorHandler("https://jsonplaceholder.typicode.com/");
  }
}
