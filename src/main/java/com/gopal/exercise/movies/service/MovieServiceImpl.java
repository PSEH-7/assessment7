package com.gopal.exercise.movies.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieServiceImpl implements MovieService {

    @Override
    public List<Object> fetchMovieDetails(String type, String name) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://swapi.co/api/planets/?page=2";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
        System.out.println(response.getBody());

        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        return new ArrayList<>();
    }
}
