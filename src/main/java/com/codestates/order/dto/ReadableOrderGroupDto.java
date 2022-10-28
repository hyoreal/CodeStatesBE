package com.codestates.order.dto;

import com.codestates.order.entity.Order;
import com.codestates.order.entity.ReadableOrderCoffee;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class ReadableOrderGroupDto {
    private long orderId;
    private long memberId;
    private Order.OrderStatus orderStatus;
    private LocalDateTime createdAt;
    public ReadableOrderGroupDto(ReadableOrderCoffee readableOrderCoffee) {
        this.orderId = readableOrderCoffee.getOrderId();
        this.memberId = readableOrderCoffee.getMemberId();
        this.orderStatus = readableOrderCoffee.getOrderStatus();
        this.createdAt = readableOrderCoffee.getCreatedAt();
    }
}
