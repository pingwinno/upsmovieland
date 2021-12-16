package com.study.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.study.movie.model.Currency;
import com.study.movie.model.Movie;
import com.study.movie.model.Order;
import com.study.movie.service.MovieService;
import com.study.web.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/movies")
@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @JsonView(Movie.WithoutDescription.class)
    @GetMapping(produces = "application/json")
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Movie getMovieById(@PathVariable Integer id, @RequestParam(defaultValue = "uah") Currency currency) {
        return movieService.getById(id, currency)
                           .orElseThrow(NotFoundException::new);
    }

    @JsonView(Movie.WithoutDescription.class)
    @GetMapping(path = "/random", produces = "application/json")
    public List<Movie> getThreeRandomMovies() {
        return movieService.getThreeRandomMovies();
    }

    @JsonView(Movie.WithoutDescription.class)
    @GetMapping(path = "/genre/{genreId}", produces = "application/json")
    public List<Movie> getMoviesByGenre(@PathVariable Integer genreId) {
        return movieService.getByGenreId(genreId);
    }

    @JsonView(Movie.WithoutDescription.class)
    @GetMapping(params = {"rating"}, produces = "application/json")
    public List<Movie> getOrderByRating() {
        return movieService.getAllOrderByRating();
    }

    @JsonView(Movie.WithoutDescription.class)
    @GetMapping(params = {"price"}, produces = "application/json")
    public List<Movie> getOrderByPrice(@RequestParam("price") Order order) {
        return movieService.getAllOrderByPrice(order);
    }
}
