package com.spring.microservices.RestTemplate_Async.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.microservices.RestTemplate_Async.client.AlbumClient;
import com.spring.microservices.RestTemplate_Async.model.Album;
import com.spring.microservices.RestTemplate_Async.model.AsyncModel;
import com.spring.microservices.RestTemplate_Async.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/rest-template-microservice")
public class ControllerClass {

    @Autowired
    AlbumClient albumClient;

    @GetMapping("/normal")
    public List<Response> getAlbum() throws JsonProcessingException {

        return albumClient.getAlbumList();
    }

    @GetMapping("/async")
    public List<AsyncModel> getAlbumAsync() throws JsonProcessingException, ExecutionException, InterruptedException {

        return albumClient.getAlbumListAsync();
    }

}
