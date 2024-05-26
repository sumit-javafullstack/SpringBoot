package com.springboot.RestTemplateMircoservices.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springboot.RestTemplateMircoservices.model.Album;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AsyncClient {

  @Autowired
  @Qualifier("restTemplateAsyncCallService")
  RestTemplate restTemplate;

  ResponseEntity<String> response;
  private final String BASE_URL = "https://jsonplaceholder.typicode.com/";

  public Collection<List<Album>> getAlbumWithoutAsync() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    Map<Integer, List<Album>> map = new HashMap<>();
    String final_url = UriComponentsBuilder.fromHttpUrl(BASE_URL).path("photos").toUriString();
    log.info("Start time with out async call {} ", LocalTime.now());
    for (int i = 0; i < 100; i++) {
      List<Album> albumList = new ArrayList<>();
      response = restTemplate.exchange(final_url, HttpMethod.GET, null, String.class);
      if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
        albumList = mapper.readValue(response.getBody(), new TypeReference<List<Album>>() {});
        map.put(i, albumList);
      }
    }
    log.info("End time with out async call {} ", LocalTime.now());
    log.info("map size {} ", map.size());
    return map.values();
  }

  public List<Album> getAlbumAsync(){

  }


}
