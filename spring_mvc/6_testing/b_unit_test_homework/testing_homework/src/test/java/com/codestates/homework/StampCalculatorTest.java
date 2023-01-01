package com.codestates.homework;

import com.codestates.helper.StampCalculator;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StampCalculatorTest {
    @Test
    @DisplayName("실습1: 스탬프 카운트 계산 단위 테스트")
    public void calculateStampCountTest() {
        // TODO 여기에 테스트 케이스를 작성해주세요.
        // given
        int nowCount = 5;
        int earned = 3;

        // when
        int actual = StampCalculator.calculateStampCount(nowCount, earned);
        int expected = 8;

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("실습1: 주문 후 누적 스탬프 카운트 계산 단위 테스트")
    public void calculateEarnedStampCountTest(){
        // TODO 여기에 테스트 케이스를 작성해주세요.
        // given
        Order order = new Order();
        OrderCoffee orderCoffee1 = new OrderCoffee();
        OrderCoffee orderCoffee2 = new OrderCoffee();
        orderCoffee1.setQuantity(3);
        orderCoffee2.setQuantity(5);
        order.setOrderCoffees(List.of(orderCoffee1, orderCoffee2));

        // when
        int result = order.getOrderCoffees().stream()
                .map(OrderCoffee::getQuantity)
                .mapToInt(quantity -> quantity)
                .sum();
        int expected = 8;

        // then
        Assertions.assertEquals(result, expected);
    }
}
