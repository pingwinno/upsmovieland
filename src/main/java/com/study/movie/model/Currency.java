package com.study.movie.model;

import java.util.Arrays;

public enum Currency {
    USD("usd"), EUR("eur"), UAH("uah");
    private final String currencyName;

    Currency(String currencyName) {
        this.currencyName = currencyName;
    }

    public static Currency getCurrencyByName(String currencyString) {
        return Arrays.stream(Currency.values())
                     .filter(currency -> currency.currencyName.equals(currencyString))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Unsupported currency: " + currencyString));
    }

    public String getCurrencyName() {
        return currencyName;
    }
}
