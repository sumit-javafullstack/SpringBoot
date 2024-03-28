package com.springboot.DISpringBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

  boolean flag = false;

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    if (response.getStatusCode() == HttpStatus.NOT_FOUND
        || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
      return true;
    }
    if (response.getStatusCode() == HttpStatus.UNAUTHORIZED
        || response.getStatusCode() == HttpStatus.FORBIDDEN) {
      return true;
    }
    return false;
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {

    if (flag
        && (response.getStatusCode() == HttpStatus.NOT_FOUND
            || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)) {
      System.out.println("Input is invalid");
    } else if (flag
        && (response.getStatusCode() == HttpStatus.UNAUTHORIZED
            || response.getStatusCode() == HttpStatus.FORBIDDEN)) {
      System.out.println("You are not authorized to access this functionality");
    } else {
      System.out.println("Something went wrong");
    }
  }
}
