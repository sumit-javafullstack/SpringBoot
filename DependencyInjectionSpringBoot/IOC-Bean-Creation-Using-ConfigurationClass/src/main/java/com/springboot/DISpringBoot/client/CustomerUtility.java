package com.springboot.DISpringBoot.client;

import com.springboot.DISpringBoot.exception.RestTemplateErrorHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerUtility {

  private final RestTemplate restTemplate;

  public ResponseEntity<List<Model>> testRestGetTemplate() {
    String uri =
        UriComponentsBuilder.fromHttpUrl("https://jsonplaceholder.typicode.com/posts")
            .toUriString();
    restTemplate.setErrorHandler(new RestTemplateErrorHandler());
    ResponseEntity<List<Model>> exchange =
        restTemplate.exchange(
            uri,
            HttpMethod.GET,
                getSecurityEntityGetRequest(),
            new ParameterizedTypeReference<List<Model>>() {});
    return exchange;
  }

  public ResponseEntity<Model> testRestPostTemplate() {
    String uri =
            UriComponentsBuilder.fromHttpUrl("https://jsonplaceholder.typicode.com/posts")
                    .toUriString();
    restTemplate.setErrorHandler(new RestTemplateErrorHandler());
    ResponseEntity<Model> exchange =
            restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    getSecurityEntityPostRequest(),
                    Model.class);
    return exchange;
  }

  private HttpEntity<String> getSecurityEntityGetRequest() {
    HttpEntity<String> httpEntityGetRequest = new HttpEntity<>(getHeaders());
    return httpEntityGetRequest;
  }

  private HttpEntity<Object> getSecurityEntityPostRequest() {
    Model obj = new Model();
    obj.setId(89);
    obj.setUserId(7888);
    obj.setBody("SUMIT");
    obj.setTitle("MANDAL");
    HttpEntity<Object> httpEntityGetRequest = new HttpEntity<>(obj,getHeaders());
    return httpEntityGetRequest;
  }

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    //        headers.add("Authorization","valueEnocded"); for Basic authentication
    //        headers.add("Authorization","Bearer token_value"); for JWT authentication
    return headers;
  }
}
