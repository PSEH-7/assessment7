package com.gopal.exercise.movies.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class MovieServiceImplTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void fetchDetailsTest(){

        movieService.fetchMovieDetails("films", "Revenge of the Sith");

    }


}