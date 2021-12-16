package com.study.movie.repository.jdbc;

import com.study.movie.model.Movie;
import com.study.movie.model.Order;
import com.study.movie.model.OrderCriteria;
import com.study.movie.repository.MovieRepository;
import com.study.movie.repository.jdbc.rowmapper.MovieMapper;
import com.study.movie.repository.jdbc.rowmapper.MovieWithDescriptionMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcMovieRepository implements MovieRepository {
    private final static String SELECT_ALL = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES;";
    private final static String SELECT_BY_ID = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, DESCRIPTION, RATING, PRICE, POSTER_LINK FROM MOVIES WHERE ID = :id;";
    private final static String SELECT_BY_GENRE_ID = "SELECT MOVIES.ID, MOVIES.NAME_RUSSIAN, MOVIES.NAME_NATIVE, MOVIES.RELEASE_DATE, MOVIES.RATING, MOVIES.PRICE, MOVIES.POSTER_LINK FROM MOVIES" +
            " JOIN GENRE_TO_MOVIE gtm ON MOVIES.ID = gtm.MOVIE_ID WHERE gtm.GENRE_ID = :genreId;";
    private final static String SELECT_RANDOM = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES ORDER BY random() LIMIT :count";
    private final static String SORT_BY = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES ORDER BY";
    private final static String SORT_BY_PRICE = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES ORDER BY PRICE";
    private final static String SORT_BY_RATING = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES ORDER BY PRICE DESC";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Movie> movieMapper = new MovieMapper();
    private final RowMapper<Movie> movieWithDescriptionMapper = new MovieWithDescriptionMapper();

    public JdbcMovieRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query(SELECT_ALL, movieMapper);
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(SELECT_BY_ID, Map.of("id", id), movieWithDescriptionMapper));
    }

    @Override
    public List<Movie> findByGenreId(Integer genreId) {
        return jdbcTemplate.query(SELECT_BY_GENRE_ID, Map.of("genreId", genreId), movieMapper);
    }

    @Override
    public List<Movie> findRandom(Integer numberOfMovies) {
        return jdbcTemplate.query(SELECT_RANDOM, Map.of("count", numberOfMovies), movieMapper);
    }

    @Override
    public List<Movie> findAllAndOrderBy(OrderCriteria orderCriteria) {
        var queryString = String.join(" ", SORT_BY, orderCriteria.getColumn(), orderCriteria.getOrder()
                                                                                            .getOrderName());
        return jdbcTemplate.query(queryString, movieMapper);
    }

    @Override
    public List<Movie> findAllAndOrderByPrice(Order order) {
        var queryString = String.join(" ", SORT_BY_PRICE, order.getOrderName());
        return jdbcTemplate.query(queryString, movieMapper);
    }

    @Override
    public List<Movie> findAllAndOrderByRating() {
        return jdbcTemplate.query(SORT_BY_RATING, movieMapper);
    }
}
