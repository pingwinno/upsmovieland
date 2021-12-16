package com.study.genre.repository.jdbc;

import com.study.genre.model.Genre;
import com.study.genre.repository.GenreRepository;
import com.study.genre.repository.jdbc.mapper.GenreMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGenreRepository implements GenreRepository {

    private final static String SELECT_ALL = "SELECT ID, GENRE_NAME FROM GENRES";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Genre> rowMapper = new GenreMapper();


    public JdbcGenreRepository(NamedParameterJdbcTemplate cachedGenreRepository) {
        this.jdbcTemplate = cachedGenreRepository;
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }
}
