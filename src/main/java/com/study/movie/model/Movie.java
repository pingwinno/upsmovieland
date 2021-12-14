package com.study.movie.model;

import lombok.Data;

@Data
public class Movie {
    private Integer id;
    private String nameRussian;
    private String nameNative;
    private Integer yearOfRelease;
    private Double rating;
    private Double price;
    private String picturePath;
}
