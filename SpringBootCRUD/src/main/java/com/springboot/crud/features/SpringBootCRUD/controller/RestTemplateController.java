package com.springboot.crud.features.SpringBootCRUD.controller;

import com.springboot.crud.features.SpringBootCRUD.model.Album;
import com.springboot.crud.features.SpringBootCRUD.service.RestTemplateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {
  @Autowired  RestTemplateImpl restTemplate;
  boolean flag;

  @GetMapping("/get")
  public List<Album> getTest() {
    List<Album> list = restTemplate.getImpl();

    return list.size() > 0 ? list: null;
  }

  @PostMapping("/post")
  public Album postTest() {
    Album album = restTemplate.postTest();

    return album != null  ? album: null;
  }
}
