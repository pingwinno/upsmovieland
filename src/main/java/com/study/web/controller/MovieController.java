package com.study.web.controller;

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

    @GetMapping(produces = "application/json")
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Movie getMovieById(@PathVariable Integer id) {
        return movieService.getById(id)
                           .orElseThrow(NotFoundException::new);
    }

    @GetMapping(path = "/random", produces = "application/json")
    public List<Movie> getThreeRandomMovies() {
        return movieService.getThreeRandomMovies();
    }

    @GetMapping(path = "/genre/{genreId}", produces = "application/json")
    public List<Movie> getThreeRandomMovies(@PathVariable Integer genreId) {
        return movieService.getByGenreId(genreId);
    }

    @GetMapping(params = {"rating"}, produces = "application/json")
    public List<Movie> getOrderByRating() {
        return movieService.getAllOrderByRating();
    }

    @GetMapping(params = {"price"}, produces = "application/json")
    public List<Movie> getOrderByPrice(@RequestParam("price") Order order) {
        return movieService.getAllOrderByPrice(order);
    }
}
