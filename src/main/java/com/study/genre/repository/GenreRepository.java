package com.study.genre.repository;

import com.study.genre.model.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> findAllByMovieId(Integer movieId);

    List<Genre> findAll();
}
