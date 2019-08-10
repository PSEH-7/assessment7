package com.gopal.exercise.movies.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MovieService {
            public List<Object> fetchMovieDetails(String type, String name);
}