package com.study.movie.repository.jdbc;

import com.study.movie.model.Movie;
import com.study.movie.repository.MovieRepository;
import com.study.movie.repository.jdbc.mapper.MovieMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class JdbcMovieRepository implements MovieRepository {
    private final static String SELECT_ALL = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES;";
    private final static String SELECT_RANDOM = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES  ORDER BY random() LIMIT :count";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Movie> rowMapper = new MovieMapper();


    public JdbcMovieRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public List<Movie> findRandom(Integer numberOfMovies) {
        return jdbcTemplate.query(SELECT_RANDOM, Map.of("count", numberOfMovies), rowMapper);
    }
}
