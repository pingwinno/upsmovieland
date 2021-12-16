package com.study.genre.service;

import com.study.genre.model.Genre;
import com.study.genre.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGenreService implements GenreService {
    private final GenreRepository genreRepository;

    public DefaultGenreService(GenreRepository cachedGenreRepository) {
        this.genreRepository = cachedGenreRepository;
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> getAllMoviesGenres(Integer movieId) {
        return genreRepository.findAllByMovieId(movieId);
    }
}
