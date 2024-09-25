package com.spring.microservices.RestTemplate_Async.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.microservices.RestTemplate_Async.model.Album;
import com.spring.microservices.RestTemplate_Async.model.AsyncModel;
import com.spring.microservices.RestTemplate_Async.model.Response;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class AlbumClient {


    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<String> response;
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public List<Response> getAlbumList() throws JsonProcessingException {
        List<Response> finalResponse = new ArrayList<>();

        List<Integer> acctList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Map<Integer, List<Album>> map = new HashMap<>();
        String final_url = UriComponentsBuilder.fromHttpUrl(BASE_URL).path("photos").toUriString();
        log.info("Start time with out async call {} ", LocalTime.now());

        for (int i = 0; i < 100; i++) {
            List<Album> albumList = new ArrayList<>();
            response = restTemplate.exchange(final_url, HttpMethod.GET, null, String.class);
            if (response.getBody() != null && response.getStatusCode().is2xxSuccessful()) {
                albumList = mapper.readValue(response.getBody(), new TypeReference<List<Album>>() {
                });

            }
            Response output = new Response();
            output.setAccount(i);
            output.setAlbumList(albumList);
            finalResponse.add(output);
        }

        log.info("End time with out async call {} ", LocalTime.now());
        log.info("List Size size {} ", finalResponse.size());
        return finalResponse;
    }

    public List<AsyncModel> getAlbumListAsync() throws JsonProcessingException, ExecutionException, InterruptedException {
        Map<Integer, CompletableFuture<ResponseEntity<String>>> futureMap = new HashMap<>();
        List<Integer> failedAccountIds = new ArrayList<>();
        Map<Integer, List<Album>> responseMap = new HashMap<>();

        List<Album> albumList = new ArrayList<>();
        List<AsyncModel> asyncModelList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String final_url = UriComponentsBuilder.fromHttpUrl(BASE_URL).path("photos").toUriString();
        log.info("Start time with async call {} ", LocalTime.now());

        for (int i = 0; i < 100; i++) {
            CompletableFuture<ResponseEntity<String>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return restTemplate.exchange(final_url, HttpMethod.GET, null, String.class);
                } catch (Exception e) {
                    return null;
                }
            });
            futureMap.put(i, future);
        }
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futureMap.values().toArray(new CompletableFuture[0])
        );

        // Block until all futures are done
        allFutures.join();

        // Process the results
        for (Map.Entry<Integer, CompletableFuture<ResponseEntity<String>>> entry : futureMap.entrySet()) {
            Integer accountId = entry.getKey();
            ResponseEntity<String> response = entry.getValue().get();
            AsyncModel model = new AsyncModel();

            if (response != null && response.getStatusCode().is2xxSuccessful()) {
                albumList = mapper.readValue(response.getBody(), new TypeReference<List<Album>>() {
                });
                responseMap.put(accountId, albumList);

            } else {
                failedAccountIds.add(accountId);
            }
            model.setFailedAccountIds(failedAccountIds);
            model.setResponseMap(responseMap);
            asyncModelList.add(model);
        }
        log.info("End time with  async call {} ", LocalTime.now());
        log.info("List Size size {} ", asyncModelList.size());
       // log.info("List Size size {} ", asyncModelList.get(2).getResponseMap().values());
        return asyncModelList;
    }
}
