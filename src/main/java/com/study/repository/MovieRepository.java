package com.study.repository;

import com.study.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findAll();

    int count();

    List<Movie> findByIds(List<Integer> ids);
}
