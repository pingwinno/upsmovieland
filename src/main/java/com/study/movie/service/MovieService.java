package com.study.movie.service;

import com.study.movie.model.Movie;
import com.study.movie.model.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> getThreeRandomMovies();

    List<Movie> getByGenreId(Integer genreId);

    List<Movie> getAllOrderBy(Map<String, String> params);

    List<Movie> getAllOrderByPrice(Order order);

    List<Movie> getAllOrderByRating();

    Optional<Movie> getById(Integer id);
}
