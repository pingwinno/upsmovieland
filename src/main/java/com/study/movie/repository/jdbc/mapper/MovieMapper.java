package com.study.movie.repository.jdbc.mapper;

import com.study.movie.model.Movie;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class MovieMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        var movie = new Movie();
        movie.setId(rs.getInt("ID"));
        movie.setNameRussian(rs.getString("NAME_RUSSIAN"));
        movie.setNameNative(rs.getString("NAME_NATIVE"));
        movie.setYearOfRelease(rs.getInt("RELEASE_DATE"));
        movie.setRating(rs.getDouble("RATING"));
        movie.setPrice(rs.getDouble("PRICE"));
        movie.setPicturePath(rs.getString("POSTER_LINK"));
        return movie;
    }
}
