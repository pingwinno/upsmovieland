package com.study.currency;

import com.study.movie.model.Currency;
import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

@Service
public class HttpCurrencyService implements CurrencyService {
    private final static String URL_TEMPLATE = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json&valcode=%s";
    private final static int ONE_DAY = 86400000;
    private final RestTemplate restTemplate;
    private final Map<Currency, java.lang.Double> currencyRates = new ConcurrentHashMap<>();

    public HttpCurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    private void initUpdater() {
        new Thread(this::updateCurrencies);
    }

    @Override
    public Double getConversionRate(Currency currency) {
        return currencyRates.get(currency);
    }

    @SneakyThrows
    private void updateCurrencies() {
        while (true) {
            for (Currency currency : Currency.values()) {
                var response = restTemplate.exchange(format(URL_TEMPLATE, currency.getCurrencyName()),
                        HttpMethod.GET, null, Money.class);

                if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                    throw new RuntimeException("Can't get rates from NBU");
                }
                var money = response.getBody();
                currencyRates.put(Currency.getCurrencyByName(money.getCc()), money.getRate());
            }
            Thread.currentThread()
                  .wait(ONE_DAY);
        }
    }

}
