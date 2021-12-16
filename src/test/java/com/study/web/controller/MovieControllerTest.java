package com.study.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.country.model.Country;
import com.study.genre.model.Genre;
import com.study.movie.model.Currency;
import com.study.movie.model.Movie;
import com.study.movie.model.Order;
import com.study.movie.service.MovieService;
import com.study.web.converter.StringToCurrencyConverter;
import com.study.web.converter.StringToOrderConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

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
        var formattingService = new FormattingConversionService();
        formattingService.addConverter(new StringToOrderConverter());
        formattingService.addConverter(new StringToCurrencyConverter());
        mockMvc = MockMvcBuilders.standaloneSetup(new MovieController(movieServiceMock))
                                 .setConversionService(formattingService)
                                 .build();
    }

    @Test
    void when_getMovieByID_then_returnMovie() throws Exception {
        var expectedGenre = new Genre();
        expectedGenre.setId(1);
        expectedGenre.setName("genre1");

        var expectedCountry = new Country();
        expectedCountry.setId(1);
        expectedCountry.setName("USA");

        var expectedMovie = new Movie();
        expectedMovie.setId(1);
        expectedMovie.setNameRussian("Прибытие поезда на вокзал Ла-Сьота");
        expectedMovie.setNameNative("The Arrival of a Train");
        expectedMovie.setDescription("Первый фильм в истории кинемагографа");
        expectedMovie.setYearOfRelease(1896);
        expectedMovie.setRating(9.9);
        expectedMovie.setPrice(19.99);
        expectedMovie.setPicturePath("http://link.com");
        expectedMovie.setGenres(List.of(expectedGenre));
        expectedMovie.setCountries(List.of(expectedCountry));

        ObjectMapper objectMapper = new ObjectMapper();
        var jsonMovie = objectMapper.writeValueAsString(expectedMovie);
        Mockito.when(movieServiceMock.getById(1, Currency.UAH))
               .thenReturn(Optional.of(expectedMovie));
        mockMvc.perform(get("/movies/1"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonMovie));
    }

    @Test
    void when_getMovieByID_then_return404() throws Exception {
        Mockito.when(movieServiceMock.getById(1, Currency.UAH))
               .thenReturn(Optional.empty());
        mockMvc.perform(get("/movies/1"))
               .andExpect(status().isNotFound());
    }

    @Test
    void when_getAllMovies_then_returnMovies() throws Exception {
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
        Mockito.when(movieServiceMock.getAll())
               .thenReturn(List.of(expectedMovie));
        mockMvc.perform(get("/movies"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonMovieList));
    }

    @Test
    void when_getRandomMovies_then_returnMovies() throws Exception {
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
        Mockito.when(movieServiceMock.getThreeRandomMovies())
               .thenReturn(List.of(expectedMovie));
        mockMvc.perform(get("/movies/random"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonMovieList));
    }

    @Test
    void when_getMoviesByGenreId_then_returnMovies() throws Exception {
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
        Mockito.when(movieServiceMock.getByGenreId(1))
               .thenReturn(List.of(expectedMovie));
        mockMvc.perform(get("/movies/genre/1"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonMovieList));
    }

    @Test
    void when_getMoviesOrderByPriceAsc_then_returnMovies() throws Exception {
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
        Mockito.when(movieServiceMock.getAllOrderByPrice(Order.ASK))
               .thenReturn(List.of(expectedMovie));
        mockMvc.perform(get("/movies?price=asc"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonMovieList));
    }

    @Test
    void when_getMoviesOrderByPriceDesc_then_returnMovies() throws Exception {
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
        Mockito.when(movieServiceMock.getAllOrderByPrice(Order.DESC))
               .thenReturn(List.of(expectedMovie));
        mockMvc.perform(get("/movies?price=desc"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonMovieList));
    }

    @Test
    void when_getMoviesOrderByRatingDesc_then_returnMovies() throws Exception {
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
        Mockito.when(movieServiceMock.getAllOrderByRating())
               .thenReturn(List.of(expectedMovie));
        mockMvc.perform(get("/movies?rating=desc"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonMovieList));
    }
}