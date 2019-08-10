package com.gopal.exercise.movies.service;

import com.gopal.exercise.movies.model.FilmDetails;
import com.gopal.exercise.movies.rest.MovieController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

//@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchDetailsTest(){

        MovieController.MovieResource movieResource =
                movieService.fetchMovieDetails("vehicles", "Sand Crawler");
        Assert.assertNotNull(movieResource);
        Assert.assertEquals(39, movieResource.getCount());
        FilmDetails filmDetails = movieResource.getFilmDetails().get(0);
        Assert.assertEquals("Attack of the Clones", filmDetails.getTitle());
        Assert.assertEquals(2, filmDetails.getEpisode_id());
        Assert.assertEquals("George Lucas", filmDetails.getDirector());
        Assert.assertEquals("Rick McCallum", filmDetails.getProducer());
        Assert.assertEquals("2002-05-16", filmDetails.getRelease_date());
    }


}