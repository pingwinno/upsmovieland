package com.study.movie.service;

import com.study.movie.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> getThreeRandomMovies();
}
