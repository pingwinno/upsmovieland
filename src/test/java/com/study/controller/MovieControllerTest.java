package com.study.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.model.Movie;
import com.study.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MovieControllerTest {

    private MovieService movieServiceMock;
    private MockMvc mockMvc;


    @BeforeEach
    void configureSystemUnderTest() {
        movieServiceMock = mock(MovieService.class);

        mockMvc = MockMvcBuilders.standaloneSetup(new MovieController(movieServiceMock))
                                 .build();
    }

    @Test
    void test() throws Exception {
        var expectedMovie = new Movie();

        expectedMovie.setId(1);
        expectedMovie.setNameRussian("Прибытие поезда на вокзал Ла-Сьота");
        expectedMovie.setNameNative("The Arrival of a Train");
        expectedMovie.setYearOfRelease(1896);
        expectedMovie.setRating(9.9);
        expectedMovie.setPrice(19.99);
        expectedMovie.setPicturePath("http://link.com");
        ObjectMapper objectMapper = new ObjectMapper();
        var jsonMovieList = objectMapper.writeValueAsString(List.of(expectedMovie));
        Mockito.when(movieServiceMock.getAllMovies())
               .thenReturn(List.of(expectedMovie));
        mockMvc.perform(get("/movies"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonMovieList));
    }

}