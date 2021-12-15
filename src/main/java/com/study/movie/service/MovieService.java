package com.study.movie.service;

import com.study.movie.model.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> getThreeRandomMovies();

    List<Movie> getByGenreId(Integer genreId);

    List<Movie> getAllOrderBy(Map<String, String> params);
}
