package com.springboot.crud.features.SpringBootCRUD.service;

import com.springboot.crud.features.SpringBootCRUD.model.Album;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Component
public class RestTemplateImpl {

  @Autowired
  @Qualifier("restTemplate")
  RestTemplate restTemplate;

  private final String BASE_URL = "https://jsonplaceholder.typicode.com/";

  public List<Album> getImpl() {
    String final_url = UriComponentsBuilder.fromHttpUrl(BASE_URL).path("photos").toUriString();
    ResponseEntity<List<Album>> response =
        restTemplate.exchange(
            final_url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {});

    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {

      List<Album> albumList = response.getBody();
      log.info("album list: " + albumList.toString());
      return albumList;
    }
    return null;
  }

  public Album postTest() {

    // Create a sample post object
    Album newAlbum = new Album();
    newAlbum.setTitle("New Post Title");
    newAlbum.setAlbumId(12345);
    newAlbum.setUrl("https://example.com/"); // Assuming there's a user with ID 1

    // Create request headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Create request entity
    HttpEntity<Album> requestEntity = new HttpEntity<>(newAlbum, headers);

    String final_url = UriComponentsBuilder.fromHttpUrl(BASE_URL).path("photos").toUriString();
    ResponseEntity<Album> response =
        restTemplate.exchange(final_url, HttpMethod.POST, requestEntity, Album.class);

    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {

      return response.getBody();
    }
    return null;
  }
}
