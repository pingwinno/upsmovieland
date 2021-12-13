package com.study.service;

import com.study.model.Movie;
import com.study.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultMovieServiceTest {
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private DefaultMovieService movieService;

    @Test
    void when_callGetAllMovies_then_returnListOfMovies() {
        var expectedMovie = new Movie();
        when(movieRepository.findAll())
                .thenReturn(List.of(expectedMovie));
        var movieList = movieService.getAllMovies();
        assertFalse(movieList.isEmpty());
        var actualMovie = movieList.get(0);
        assertEquals(expectedMovie, actualMovie);
        verify(movieRepository).findAll();
        verifyNoMoreInteractions(movieRepository);
    }
}