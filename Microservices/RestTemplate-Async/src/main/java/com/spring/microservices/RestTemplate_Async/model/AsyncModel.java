package com.spring.microservices.RestTemplate_Async.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AsyncModel {
    private Map<Integer, List<Album>> responseMap;
    private List<Integer> failedAccountIds;
}
