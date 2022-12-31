package com.codestates.order.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCoffeeResponseDto {
    private long coffeeId;
    private Integer quantity;
    private String korName;
    private String engName;
    private Integer price;
}
