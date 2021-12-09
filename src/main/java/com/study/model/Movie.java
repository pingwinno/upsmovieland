package com.study.model;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;
}
