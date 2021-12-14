package com.study.web.controller;

import com.study.movie.model.Movie;
import com.study.movie.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/movies")
@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(produces = "application/json")
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @GetMapping(path = "/random", produces = "application/json")
    public List<Movie> getThreeRandomMovies() {
        return movieService.getThreeRandomMovies();
    }
}
