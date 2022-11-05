package com.codestates.coffee.entity;

import com.codestates.values.Money;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;

@Getter
@Setter
public class Coffee {
    @Id
    private long coffeeId;

    private String korName;
    private String engName;
    private Money price;
    private String coffeeCode;
}
