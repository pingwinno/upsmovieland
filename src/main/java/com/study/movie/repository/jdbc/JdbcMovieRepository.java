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
    private final static String SELECT_BY_GENRE_ID = "SELECT MOVIES.ID, MOVIES.NAME_RUSSIAN, MOVIES.NAME_NATIVE, MOVIES.RELEASE_DATE, MOVIES.RATING, MOVIES.PRICE, MOVIES.POSTER_LINK FROM MOVIES" +
            " JOIN GENRE_TO_MOVIE gtm ON MOVIES.ID = gtm.MOVIE_ID " +
            " WHERE gtm.GENRE_ID = :genreId;";
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
    public List<Movie> findByGenreId(Integer genreId) {
        return jdbcTemplate.query(SELECT_BY_GENRE_ID, Map.of("genreId", genreId), rowMapper);
    }

    @Override
    public List<Movie> findRandom(Integer numberOfMovies) {
        return jdbcTemplate.query(SELECT_RANDOM, Map.of("count", numberOfMovies), rowMapper);
    }
}
