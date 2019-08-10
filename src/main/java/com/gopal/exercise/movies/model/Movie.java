package com.gopal.exercise.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


public class Movie {
    private List<? extends MovieDetails> results;

//    @Override
//    public String toString(){
//        return "results[name"+name+"]";
//    }
}
