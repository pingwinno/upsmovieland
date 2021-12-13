package com.study.service;

import com.study.exception.NotFoundException;
import com.study.model.Movie;
import com.study.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DefaultMovieService implements MovieService {
    private final MovieRepository movieRepository;

    public DefaultMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getThreeRandomMovies() {
        var moviesCount = movieRepository.count();
        if (moviesCount == 0) {
            throw new NotFoundException("Can't find any movie");
        }
        var random = ThreadLocalRandom.current();
        var randomMovieIds = new HashSet<Integer>();
        while (randomMovieIds.size() != moviesCount && randomMovieIds.size() != 3) {
            randomMovieIds.add(random.nextInt(1, moviesCount + 1));
        }
        return movieRepository.findByIds(randomMovieIds);
    }
}
