package com.study.movie.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderCriteria {
    private String column;
    private Order order;
}
