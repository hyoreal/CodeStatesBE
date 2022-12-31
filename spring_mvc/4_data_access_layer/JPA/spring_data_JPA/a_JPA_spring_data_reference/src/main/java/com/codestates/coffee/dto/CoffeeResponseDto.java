package com.codestates.coffee.dto;

import com.codestates.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
public class CoffeeResponseDto {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;
    private Coffee.CoffeeStatus coffeeStatus; // TODO 추가된 부분

    public String getCoffeeStatus() {
        return coffeeStatus.getStatus();
    }
}
