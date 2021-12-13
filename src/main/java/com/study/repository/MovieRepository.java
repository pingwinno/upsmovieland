package com.study.repository;

import com.study.model.Movie;

import java.util.List;
import java.util.Set;

public interface MovieRepository {
    List<Movie> findAll();

    int count();

    List<Movie> findByIds(Set<Integer> ids);
}
