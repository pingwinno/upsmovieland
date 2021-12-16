package com.study.country.repository.jdbc;

import com.study.country.model.Country;
import com.study.country.repository.CountryRepository;
import com.study.country.repository.jdbc.rowmapper.CountryMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class JdbcCountryRepository implements CountryRepository {

    private final static String SELECT_ALL = "SELECT ID, COUNTRY FROM COUNTRIES";
    private final static String SELECT_ALL_BY_IDS = "SELECT ID, COUNTRY FROM COUNTRIES JOIN COUNTRY_TO_MOVIE ctm ON COUNTRIES.ID = ctm.COUNTRY_ID WHERE ctm.MOVIE_ID = :movieId";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Country> countryMapper = new CountryMapper();

    public JdbcCountryRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Country> findAllByMovieId(Integer movieId) {
        return jdbcTemplate.query(SELECT_ALL_BY_IDS, Map.of("movieId", movieId), countryMapper);
    }

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(SELECT_ALL, countryMapper);
    }
}
