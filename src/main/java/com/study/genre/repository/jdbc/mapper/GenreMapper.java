package com.study.genre.repository.jdbc.mapper;

import com.study.genre.model.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        var genre = new Genre();
        genre.setId(rs.getInt("ID"));
        genre.setName(rs.getString("GENRE_NAME"));
        return genre;
    }
}
