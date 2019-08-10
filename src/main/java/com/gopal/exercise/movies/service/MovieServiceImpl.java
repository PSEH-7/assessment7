package com.gopal.exercise.movies.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopal.exercise.movies.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component

public class MovieServiceImpl implements MovieService {

    Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Override
    public List<Object> fetchMovieDetails(String type, String name) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://swapi.co/api/";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


        ResponseEntity<String> response
                = new RestTemplate().exchange(fooResourceUrl+type, HttpMethod.GET, entity, String.class);

        System.out.println(response.getBody());

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            // Java object to JSON file
            Movie movie = mapper.readValue(response.getBody(), Movie.class);

            // Java object to JSON string
            System.out.println("movie" + movie);

        }catch (IOException ioex){
            logger.error("Error while parsing object");
        }

        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        return new ArrayList<>();
    }
}
