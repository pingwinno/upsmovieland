package com.study.repository.jdbc;

import com.study.model.Movie;
import com.study.repository.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.StringJoiner;

@Repository
public class JdbcMovieRepository implements MovieRepository {
    private final static String SELECT_ALL = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES;";
    private final static String SELECT_BY_IDS = "SELECT ID, NAME_RUSSIAN, NAME_NATIVE, RELEASE_DATE, RATING, PRICE, POSTER_LINK FROM MOVIES WHERE ID IN ";
    private final static String COUNT = "SELECT COUNT(ID) FROM MOVIES;";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Movie> rowMapper;

    public JdbcMovieRepository(DataSource dataSource, RowMapper<Movie> rowMapper) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
    }

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject(COUNT, Integer.class);
    }

    @Override
    public List<Movie> findByIds(List<Integer> ids) {
        var stringJoiner = new StringJoiner(",", " (", ");");
        for (int i = 0; i < ids.size(); i++) {
            stringJoiner.add("?");
        }
        var queryString = SELECT_BY_IDS + stringJoiner;
        return jdbcTemplate.queryForList(queryString, Movie.class, ids);
    }
}
