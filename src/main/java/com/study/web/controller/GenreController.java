package com.study.web.controller;

import com.study.genre.model.Genre;
import com.study.genre.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/genres")
@RestController
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(produces = "application/json")
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }
}
