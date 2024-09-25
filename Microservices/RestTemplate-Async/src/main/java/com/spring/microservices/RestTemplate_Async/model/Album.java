package com.spring.microservices.RestTemplate_Async.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    private int id;
    private int albumId;
    private String url;
    private String title;
    private String thumbnailUrl;

}
