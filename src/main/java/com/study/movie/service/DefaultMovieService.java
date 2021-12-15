package com.study.movie.service;

import com.study.movie.model.Movie;
import com.study.movie.model.Order;
import com.study.movie.model.OrderCriteria;
import com.study.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DefaultMovieService implements MovieService {
    private final static Map<String, List<String>> ALLOWED_FIELDS = Map.of("rating", List.of("desc"), "price",
            List.of("asc", "desc"));
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


    /**
     * Returns all movies ordered by price or rating. Only one param at once is allowed.
     *
     * @param params map of column and sorting order. Accept only "price" or "rating" fields.
     *               Rating field is allowed to have only "desc" order. For price is allowed "asc" and "desc".
     * @return List of sorted movies
     * @throws IllegalArgumentException when key params (fields) are not "price" or "rating"
     * @throws IllegalArgumentException when map has more than one entity
     */
    @Override
    public List<Movie> getAllOrderBy(Map<String, String> params) {
        if (params.size() > 1) {
            throw new IllegalArgumentException("Too many arguments");
        }
        var orderCriteria = params.entrySet()
                                  .stream()
                                  .filter(entry -> ALLOWED_FIELDS.containsKey(entry.getKey()) &&
                                          ALLOWED_FIELDS.get(entry.getKey())
                                                        .contains(entry.getValue()))
                                  .map(entry -> OrderCriteria.builder()
                                                             .column(entry.getKey())
                                                             .order(Order.getOrderByName(entry.getValue()))
                                                             .build())
                                  .findFirst()
                                  .orElseThrow(() -> new IllegalArgumentException("Wrong arguments: " + params));
        return movieRepository.findAllAndOrderBy(orderCriteria);
    }
}
