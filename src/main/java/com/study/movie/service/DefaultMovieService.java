package com.study.movie.service;

import com.study.movie.model.Movie;
import com.study.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {
    private final MovieRepository movieRepository;

    public DefaultMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getThreeRandomMovies() {
        return movieRepository.findRandom(3);
    }

    @Override
    public List<Movie> getByGenreId(Integer genreId) {
        return movieRepository.findByGenreId(genreId);
    }
}
