package com.study.controller;

import com.study.model.Movie;
import com.study.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/api/v1/movies")
@Controller
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
