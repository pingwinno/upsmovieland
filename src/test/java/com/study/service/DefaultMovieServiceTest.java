package com.study.service;

import com.study.exception.NotFoundException;
import com.study.model.Movie;
import com.study.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anySet;
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

    @Test
    void when_callGetThreeRandomMovies_then_returnListOfThreeMovies() {
        when(movieRepository.count())
                .thenReturn(3);
        when(movieRepository.findByIds(Set.of(1, 2, 3)))
                .thenReturn(List.of(new Movie(), new Movie(), new Movie()));
        var movieList = movieService.getThreeRandomMovies();
        assertFalse(movieList.isEmpty());
        assertEquals(3, movieList.size());
        verify(movieRepository).count();
        verify(movieRepository).findByIds(Set.of(1, 2, 3));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void when_callGetThreeRandomMoviesWithFiveMoviesExists_then_returnListOfThreeMovies() {
        when(movieRepository.count())
                .thenReturn(5);
        when(movieRepository.findByIds(anySet()))
                .thenReturn(List.of(new Movie(), new Movie(), new Movie()));
        var movieList = movieService.getThreeRandomMovies();
        assertFalse(movieList.isEmpty());
        assertEquals(3, movieList.size());
        verify(movieRepository).count();
        verify(movieRepository).findByIds(anySet());
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void when_callGetThreeRandomMoviesWithOnlyTwoMoviesExists_then_returnListOfTwoMovies() {
        when(movieRepository.count())
                .thenReturn(2);
        when(movieRepository.findByIds(Set.of(1, 2)))
                .thenReturn(List.of(new Movie(), new Movie()));
        var movieList = movieService.getThreeRandomMovies();
        assertFalse(movieList.isEmpty());
        assertEquals(2, movieList.size());
        verify(movieRepository).count();
        verify(movieRepository).findByIds(Set.of(1, 2));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void when_moviesTableIsEmpty_then_throwNotFoundException() {
        when(movieRepository.count())
                .thenReturn(0);
        assertThrows(NotFoundException.class, () -> movieService.getThreeRandomMovies());
    }
}