package com.study.web.converter;

import com.study.movie.model.Order;
import org.springframework.core.convert.converter.Converter;

public class StringToOrderConverter implements Converter<String, Order> {
    @Override
    public Order convert(String source) {
        return Order.getOrderByName(source);
    }
}
