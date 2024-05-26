package com.springboot._CircuitBreakerConsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot._CircuitBreakerConsumer.model.Employee;

import java.util.List;

public interface ProducerService {

    public List<Employee> getEmployees() throws JsonProcessingException;
}
