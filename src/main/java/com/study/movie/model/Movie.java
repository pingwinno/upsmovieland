package com.study.movie.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.country.model.Country;
import com.study.genre.model.Genre;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie {
    private Integer id;
    private String nameRussian;
    private String nameNative;
    private Integer yearOfRelease;
    private String description;
    private Double rating;
    private Double price;
    private String picturePath;
    private List<Genre> genres;
    private List<Country> countries;
}
