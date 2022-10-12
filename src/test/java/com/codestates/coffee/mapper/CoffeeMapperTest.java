package com.codestates.coffee.mapper;

import com.codestates.coffee.dto.CoffeePostDto;
import com.codestates.coffee.entity.Coffee;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CoffeeMapperTest {
    @Test
    public void coffeePostDtoToCoffeeTest() {
        CoffeePostDto coffeePostDto = new CoffeePostDto();
        coffeePostDto.setKorName("카페라떼");
        coffeePostDto.setEngName("Cafe Latte");
        coffeePostDto.setPrice(4000);

        Coffee coffee = CoffeeMapper.INSTANCE.coffeePostDtoToCoffee(coffeePostDto);

        assertThat(coffee.getKorName(), is(coffeePostDto.getKorName()));
        assertThat(coffee.getEngName(), is(coffeePostDto.getEngName()));
        assertThat(coffee.getPrice(), is(coffeePostDto.getPrice()));
    }
}
