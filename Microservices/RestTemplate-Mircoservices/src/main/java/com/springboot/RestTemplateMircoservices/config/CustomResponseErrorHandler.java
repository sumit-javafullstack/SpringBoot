package com.springboot.RestTemplateMircoservices.config;

import com.springboot.RestTemplateMircoservices.exception.EmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class CustomResponseErrorHandler implements ResponseErrorHandler {
  String serviceName;
  public CustomResponseErrorHandler(String serviceName) {
    this.serviceName = serviceName;
  }

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    // Check if the response status code indicates an error (4xx or 5xx)
    return httpResponse.getStatusCode().isError();
  }

  @Override
  public void handleError(ClientHttpResponse httpResponse) throws IOException {
    int statusCode = httpResponse.getStatusCode().value();

    if (statusCode == HttpStatus.NOT_FOUND.value()) {
      throw new EmployeeException(serviceName+" Resource not found" , statusCode);
    } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
      throw new EmployeeException(serviceName+" Invalid request", statusCode);
    } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
      throw new EmployeeException(serviceName+" Unauthorized access", statusCode);
    } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
      throw new EmployeeException(serviceName+" Access forbidden", statusCode);
    } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
      throw new EmployeeException(serviceName+" Internal server error", statusCode);
    } else {
      // For other error codes, throw a generic exception
      throw new EmployeeException("Received HTTP error: " + statusCode);
    }
  }
}
