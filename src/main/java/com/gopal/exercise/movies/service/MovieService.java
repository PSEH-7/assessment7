package com.gopal.exercise.movies.service;

import com.gopal.exercise.movies.rest.MovieController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieService {
            public MovieController.MovieResource fetchMovieDetails(String type, String name);
}