package com.springboot.DISpringBoot.configuration;

import com.springboot.DISpringBoot.client.CustomerUtility;
import com.springboot.DISpringBoot.repository.EmployeeDaoImpl;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

@Configuration
public class CustomBeanCreation {

  @Bean
  public EmployeeDaoImpl creatBeanEmployeeDaoImpl() {
    return new EmployeeDaoImpl();
  }

  @Bean
  public RestTemplate restTemplateCustomBean() {

    // return new RestTemplate(); -> spring managed bean
    // Creating own bean
    RestTemplate restTemplate = new RestTemplate();

    HttpComponentsClientHttpRequestFactory httpRequestFactory =
        new HttpComponentsClientHttpRequestFactory();
    httpRequestFactory.setConnectionRequestTimeout(20000);
    httpRequestFactory.setConnectTimeout(60000);

    // disabling browser cookie
    HttpClient httpClient = HttpClientBuilder.create().disableCookieManagement().build();
    httpRequestFactory.setHttpClient(httpClient);
    // Encoding URI variables passing through link
    restTemplate.setUriTemplateHandler(uriTemplateHandler());
    restTemplate.setRequestFactory(httpRequestFactory);
    return restTemplate;
  }

  private UriTemplateHandler uriTemplateHandler() {
      /*
      In RestTemplate configuration, you typically don't add encoding to the URL directly; rather,
      you handle encoding through URI templates or request parameters. When you construct URI
      templates or query parameters, the RestTemplate library automatically handles encoding for you.
      However, if you need to customize or override the default encoding behavior, you can do so
      by providing a custom UriTemplateHandler
       */

    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
    return factory;
  }

}
