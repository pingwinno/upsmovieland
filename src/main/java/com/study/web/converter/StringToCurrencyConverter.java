package com.study.web.converter;

import com.study.movie.model.Currency;
import org.springframework.core.convert.converter.Converter;

public class StringToCurrencyConverter implements Converter<String, Currency> {
    @Override
    public Currency convert(String source) {
        return Currency.getCurrencyByName(source.toLowerCase());
    }
}
