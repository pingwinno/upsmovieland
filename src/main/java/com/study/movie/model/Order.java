package com.study.movie.model;

import java.util.Arrays;

public enum Order {
    ASK("asc"), DESC("desc");
    private final String orderName;

    Order(String order) {
        this.orderName = order;
    }

    public static Order getOrderByName(String orderString) {
        return Arrays.stream(Order.values())
                     .filter(order -> order.orderName.equals(orderString))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Wrong order type: " + orderString));
    }

    public String getOrderName() {
        return orderName;
    }
}
