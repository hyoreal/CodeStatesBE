package com.codestates.coffee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coffee {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;
}
