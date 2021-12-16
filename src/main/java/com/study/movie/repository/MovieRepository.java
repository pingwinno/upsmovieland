package com.study.movie.repository;

import com.study.movie.model.Movie;
import com.study.movie.model.Order;
import com.study.movie.model.OrderCriteria;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    List<Movie> findAll();

    Optional<Movie> findById(Integer id);

    List<Movie> findByGenreId(Integer genreId);

    List<Movie> findRandom(Integer numberOfMovies);

    List<Movie> findAllAndOrderBy(OrderCriteria orderCriteria);

    List<Movie> findAllAndOrderByPrice(Order order);

    List<Movie> findAllAndOrderByRating();
}
