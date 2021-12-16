package com.study.country.repository.jdbc.rowmapper;

import com.study.country.model.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        var country = new Country();
        country.setId(rs.getInt("ID"));
        country.setName(rs.getString("COUNTRY"));
        return country;
    }
}
