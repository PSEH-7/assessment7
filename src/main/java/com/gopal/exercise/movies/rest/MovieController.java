package com.gopal.exercise.movies.rest;

import com.gopal.exercise.movies.model.FilmDetails;
import com.gopal.exercise.movies.model.MovieDetails;
import com.gopal.exercise.movies.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(path = "/details")
    public ResponseEntity<?> fetchPlanetDetails(@RequestParam(value = "type") String type,
                                                @RequestParam(value = "name", required = false) String name){
        MovieResource movieResource = movieService.fetchMovieDetails(type, name);
        return ResponseEntity.ok(movieResource);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MovieResource{
        private int count;
        private List<FilmDetails> filmDetails;
    }


}
