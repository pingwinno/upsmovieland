package com.study.country.repository;

import com.study.country.model.Country;

import java.util.List;

public interface CountryRepository {
    List<Country> findAllByMovieId(Integer movieId);

    List<Country> findAll();
}
