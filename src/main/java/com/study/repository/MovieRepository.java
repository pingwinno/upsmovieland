package com.study.repository;

import com.study.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getAllMovies();
}
