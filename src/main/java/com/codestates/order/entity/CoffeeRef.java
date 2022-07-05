package com.codestates.order.entity;

import com.codestates.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@AllArgsConstructor
@Table("ORDER_COFFEE")
public class CoffeeRef {
    // AggregateReference로 감싸지 않아도 되지만 Java는 참조 관점에서 생각하므로 감싸주도록 하자.
    private AggregateReference<Coffee, Long> coffeeId;
    private int quantity;
}
