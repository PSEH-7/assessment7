package com.gopal.exercise.movies.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopal.exercise.movies.model.FilmDetails;
import com.gopal.exercise.movies.model.Movie;
import com.gopal.exercise.movies.model.MovieDetails;
import com.gopal.exercise.movies.rest.MovieController;
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

    private static final String SW_API = "https://swapi.co/api/";

    @Override
    public MovieController.MovieResource fetchMovieDetails(String type, String inName) {
        MovieController.MovieResource movieResource = new MovieController.MovieResource();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response
                = new RestTemplate().exchange(SW_API+type, HttpMethod.GET, entity, String.class);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Movie movie = mapper.readValue(response.getBody(), Movie.class);

            if(inName != null && !inName.isEmpty())
            {
                List<FilmDetails> filmDetails = new ArrayList<>();
                for(MovieDetails movieDetails : movie.getResults()){
                    if(movieDetails.getName().equalsIgnoreCase(inName)){
                        for(String filmURI : movieDetails.getFilms()){
                            response= new RestTemplate().
                                    exchange(filmURI, HttpMethod.GET, entity, String.class);
                            mapper = new ObjectMapper();
                            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                            FilmDetails filmDetails1 = mapper.readValue(response.getBody(), FilmDetails.class);
                            filmDetails.add(filmDetails1);
                        }
                        break;
                    }
                }
                if(!filmDetails.isEmpty()){
                    movieResource.setFilmDetails(filmDetails);
                }
                movieResource.setCount(movie.getCount());
            }else{
                movieResource.setCount(movie.getCount());
                movieResource.setFilmDetails(new ArrayList<>());
            }
        }catch (IOException ioex){
            logger.error("Error while parsing object");
        }
        return movieResource;
    }
}
