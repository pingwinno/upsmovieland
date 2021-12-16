package com.study.country.service;

import com.study.country.model.Country;
import com.study.country.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCountryService implements CountryService {
    private final CountryRepository countryRepository;

    public DefaultCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getCountriesOfMovie(Integer movieId) {
        return countryRepository.findAllByMovieId(movieId);
    }
}
