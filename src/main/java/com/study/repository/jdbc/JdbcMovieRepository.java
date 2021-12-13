package com.study.repository.jdbc;

import com.study.model.Movie;
import com.study.repository.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcMovieRepository implements MovieRepository {
    private final static String SELECT_ALL = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES;";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Movie> rowMapper;

    public JdbcMovieRepository(DataSource dataSource, RowMapper<Movie> rowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
    }

    @Override
    public List<Movie> getAllMovies() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }
}
