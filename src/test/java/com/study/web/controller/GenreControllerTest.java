package com.study.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.genre.model.Genre;
import com.study.genre.service.GenreService;
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


class GenreControllerTest {

    private GenreService genreServiceMock;
    private MockMvc mockMvc;


    @BeforeEach
    void configureSystemUnderTest() {
        genreServiceMock = mock(GenreService.class);

        mockMvc = MockMvcBuilders.standaloneSetup(new GenreController(genreServiceMock))
                                 .build();
    }

    @Test
    void when_getAllMovies_then_returnMovies() throws Exception {
        var expectedGenre = new Genre();

        expectedGenre.setId(1);
        expectedGenre.setName("genre1");
        ObjectMapper objectMapper = new ObjectMapper();
        var jsonMovieList = objectMapper.writeValueAsString(List.of(expectedGenre));
        Mockito.when(genreServiceMock.getAll())
               .thenReturn(List.of(expectedGenre));
        mockMvc.perform(get("/genres"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonMovieList));
    }
}