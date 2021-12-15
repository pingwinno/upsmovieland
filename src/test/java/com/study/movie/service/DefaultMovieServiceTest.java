package com.study.movie.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultMovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
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


}