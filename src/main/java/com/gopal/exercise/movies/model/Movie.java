package com.gopal.exercise.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private int count;
    private List<? extends MovieDetails> results = new ArrayList<>();
}
