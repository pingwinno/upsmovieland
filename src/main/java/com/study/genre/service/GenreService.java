package com.study.genre.service;

import com.study.genre.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();

    List<Genre> getAllMoviesGenres(Integer movieId);
}
