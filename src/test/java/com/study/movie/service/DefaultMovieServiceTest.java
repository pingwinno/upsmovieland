package com.study.movie.service;

import com.study.country.model.Country;
import com.study.country.service.CountryService;
import com.study.genre.model.Genre;
import com.study.genre.service.GenreService;
import com.study.movie.model.Movie;
import com.study.movie.model.OrderCriteria;
import com.study.movie.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultMovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private GenreService genreService;
    @Mock
    private CountryService countryService;
    @InjectMocks
    private DefaultMovieService movieService;


    @Test
    void given_mapWithTwoParams_when_getAllOrderBy_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> movieService.getAllOrderBy(Map.of("1", "", "2", "")));
        verifyNoInteractions(movieRepository);
    }

    @Test
    void given_mapWithWrongParam_when_getAllOrderBy_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> movieService.getAllOrderBy(Map.of("price", "ewfwef")));
        verifyNoInteractions(movieRepository);
    }

    @Test
    void given_mapWithNotAllowedField_when_getAllOrderBy_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> movieService.getAllOrderBy(Map.of("someField", "asc")));
        verifyNoInteractions(movieRepository);
    }

    @Test
    void given_mapWithRatingAndAscOrder_when_getAllOrderBy_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> movieService.getAllOrderBy(Map.of("rating", "asc")));
        verifyNoInteractions(movieRepository);
    }

    @Test
    void given_mapWithPrice_when_getAllOrderBy_thenThrowIllegalArgumentException() {
        var expectedMoviesList = List.of(new Movie());
        when(movieRepository.findAllAndOrderBy(any(OrderCriteria.class))).thenReturn(expectedMoviesList);
        var actualMoviesList = movieService.getAllOrderBy(Map.of("price", "asc"));
        assertEquals(expectedMoviesList, actualMoviesList);
        verify(movieRepository).findAllAndOrderBy(any(OrderCriteria.class));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void given_movieId_when_getById_thenReturnMovieWithMetadata() {
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


        when(movieRepository.findById(1)).thenReturn(Optional.of(expectedMovie));
        when(countryService.getCountriesOfMovie(1)).thenReturn(List.of(expectedCountry));
        when(genreService.getAllMoviesGenres(1)).thenReturn(List.of(expectedGenre));
        var optionalMovie = movieService.getById(1);
        assertTrue(optionalMovie.isPresent());
        var actualMovie = optionalMovie.get();
        assertEquals(1, actualMovie.getId());
        assertEquals("Прибытие поезда на вокзал Ла-Сьота", actualMovie.getNameRussian());
        assertEquals("The Arrival of a Train", actualMovie.getNameNative());
        assertEquals("Первый фильм в истории кинемагографа", actualMovie.getDescription());
        assertEquals(1896, actualMovie.getYearOfRelease());
        assertEquals(9.9, actualMovie.getRating());
        assertEquals(19.99, actualMovie.getPrice());
        assertEquals("http://link.com", actualMovie.getPicturePath());
        assertEquals(expectedGenre, actualMovie.getGenres()
                                               .get(0));
        assertEquals(expectedCountry, actualMovie.getCountries()
                                                 .get(0));

        verify(movieRepository).findById(1);
        verify(countryService).getCountriesOfMovie(1);
        verify(genreService).getAllMoviesGenres(1);
        verifyNoMoreInteractions(movieRepository, countryService, genreService);
    }

}