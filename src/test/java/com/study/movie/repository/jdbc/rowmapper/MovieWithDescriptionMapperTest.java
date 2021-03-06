package com.study.movie.repository.jdbc.rowmapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieWithDescriptionMapperTest {
    @Mock
    private ResultSet rs;
    @InjectMocks
    private MovieWithDescriptionMapper mapper;

    @BeforeEach
    void init() throws SQLException {
        when(rs.getInt("ID")).thenReturn(1);
        when(rs.getString("NAME_RUSSIAN")).thenReturn("Прибытие поезда на вокзал Ла-Сьота");
        when(rs.getString("NAME_NATIVE")).thenReturn("The Arrival of a Train");
        when(rs.getInt("RELEASE_DATE")).thenReturn(1896);
        when(rs.getString("DESCRIPTION")).thenReturn("Первый фильм в истории кинемагографа");
        when(rs.getDouble("RATING")).thenReturn(9.9);
        when(rs.getDouble("PRICE")).thenReturn(19.99);
        when(rs.getString("POSTER_LINK")).thenReturn("http://link.com");
    }

    @AfterEach
    void tearDown() throws SQLException {
        verify(rs).getInt("ID");
        verify(rs).getString("NAME_RUSSIAN");
        verify(rs).getString("NAME_NATIVE");
        verify(rs).getInt("RELEASE_DATE");
        verify(rs).getString("DESCRIPTION");
        verify(rs).getDouble("RATING");
        verify(rs).getDouble("PRICE");
        verify(rs).getString("POSTER_LINK");
        verifyNoMoreInteractions(rs);
    }

    @Test
    void givenResultSet_whenCallMapToRow_thenReturnPopulatedMovieObject() throws SQLException {
        var movieObject = mapper.mapRow(rs, 1);
        assertEquals(1, movieObject.getId());
        assertEquals("Прибытие поезда на вокзал Ла-Сьота", movieObject.getNameRussian());
        assertEquals("The Arrival of a Train", movieObject.getNameNative());
        assertEquals("Первый фильм в истории кинемагографа", movieObject.getDescription());
        assertEquals(1896, movieObject.getYearOfRelease());
        assertEquals(9.9, movieObject.getRating());
        assertEquals(19.99, movieObject.getPrice());
        assertEquals("http://link.com", movieObject.getPicturePath());
    }
}