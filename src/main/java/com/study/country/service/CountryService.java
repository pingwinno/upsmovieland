package com.study.country.service;

import com.study.country.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> getCountriesOfMovie(Integer movieId);
}
