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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class AsyncClient {

  @Autowired
  @Qualifier("restTemplateAsyncCallService")
  RestTemplate restTemplate;

  ResponseEntity<String> response;
  private final String BASE_URL = "https://jsonplaceholder.typicode.com/";
  String final_url = UriComponentsBuilder.fromHttpUrl(BASE_URL).path("photos").toUriString();
  ObjectMapper mapper = new ObjectMapper();

  public Collection<List<Album>> getAlbumWithoutAsync() throws JsonProcessingException {

    mapper.registerModule(new JavaTimeModule());
    Map<Integer, List<Album>> map = new HashMap<>();

    log.info("**Start time with out async call {} **", LocalTime.now());
    for (int i = 0; i < 100; i++) {
      List<Album> albumList = new ArrayList<>();
      response = restTemplate.exchange(final_url, HttpMethod.GET, null, String.class);
      if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
        albumList = mapper.readValue(response.getBody(), new TypeReference<List<Album>>() {});
        map.put(i, albumList);
      }
    }
    log.info("**End time with out async call {} **", LocalTime.now());
    log.info("map size {} ", map.size());
    return map.values();
  }

  public List<Album> getAlbumAsync()
      throws ExecutionException, InterruptedException, JsonProcessingException {

    Map<Integer, CompletableFuture<ResponseEntity<String>>> futureMap = new HashMap<>();
    List<Integer> failedAccountIds = new ArrayList<>();
    Map<Integer, List<Album>> responseMap = new HashMap<>();
    mapper.registerModule(new JavaTimeModule());
    log.info("**Start time with async call {} **", LocalTime.now());
    for (int i = 0; i < 100; i++) {
      CompletableFuture<ResponseEntity<String>> future =
          CompletableFuture.supplyAsync(
              () -> {
                try {
                  return restTemplate.exchange(final_url, HttpMethod.GET, null, String.class);
                } catch (Exception e) {
                  return null;
                }
              });
      futureMap.put(i, future);
    }
    // Wait for all futures to complete
    CompletableFuture<Void> allFutures =
        CompletableFuture.allOf(futureMap.values().toArray(new CompletableFuture[0]));
    // Block until all futures are done
    allFutures.join();

    // Process the results
    for (Map.Entry<Integer, CompletableFuture<ResponseEntity<String>>> entry :
        futureMap.entrySet()) {
      Integer accountId = entry.getKey();
      Boolean isSuccessful = entry.getValue().get().getStatusCode().is2xxSuccessful();

      if (entry.getValue().get().getBody() != null && isSuccessful) {

        List<Album> albumList =
            mapper.readValue(entry.getValue().get().getBody(), new TypeReference<List<Album>>() {});
        responseMap.put(accountId, albumList);
      } else {
        failedAccountIds.add(accountId);
      }
    }
    log.info("**End time with async call {} **", LocalTime.now());
    log.info("map.size "+responseMap.size());
    return null;
  }
}
