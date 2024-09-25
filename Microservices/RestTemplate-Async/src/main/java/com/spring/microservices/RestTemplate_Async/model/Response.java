package com.spring.microservices.RestTemplate_Async.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<Album> albumList;
    private int account;
}
