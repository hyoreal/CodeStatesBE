package com.codestates.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private long memberId;
    private long coffeeId;
}
