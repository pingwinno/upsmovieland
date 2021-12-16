package com.study.movie.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.study.country.model.Country;
import com.study.genre.model.Genre;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Movie {
    @JsonView(Movie.WithoutDescription.class)
    private Integer id;
    @JsonView(Movie.WithoutDescription.class)
    private String nameRussian;
    @JsonView(Movie.WithoutDescription.class)
    private String nameNative;
    @JsonView(Movie.WithoutDescription.class)
    private Integer yearOfRelease;
    private String description;
    @JsonView(Movie.WithoutDescription.class)
    private Double rating;
    @JsonView(Movie.WithoutDescription.class)
    private Double price;
    @JsonView(Movie.WithoutDescription.class)
    private String picturePath;
    private List<Genre> genres = new ArrayList<>();
    private List<Country> countries = new ArrayList<>();

    public interface WithoutDescription {
    }
}
