package com.study.currency;

import com.study.movie.model.Currency;

public interface CurrencyService {
    Double convertFromUahTo(Double price, Currency currency);
}
