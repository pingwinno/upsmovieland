package com.study.movie.repository;

import com.study.movie.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findAll();

    List<Movie> findByGenreId(Integer genreId);

    List<Movie> findRandom(Integer numberOfMovies);
}
