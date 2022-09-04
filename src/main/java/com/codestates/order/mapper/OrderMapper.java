package com.codestates.order.mapper;

import com.codestates.coffee.entity.Coffee;
import com.codestates.member.entity.Member;
import com.codestates.order.dto.*;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
//    Order orderPostDtoToOrder(OrderPostDto orderPostDto);
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);

//    OrderResponseDto orderToOrderResponseDto(Order order, List<Coffee> coffees);

    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);

    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        Member member = new Member();
        member.setMemberId(orderPostDto.getMemberId());

        orderPostDto.getOrderCoffees().stream()
                .map(orderCoffeeDto -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
                    orderCoffee.addCoffee(coffee);
                    orderCoffee.addOrder(order);
                    orderCoffee.setQuantity(orderCoffeeDto.getQuantity());
                    return orderCoffee;
                }).forEach(order::addOrderCoffees);

        order.setMember(member);

        return order;
    }

    default OrderResponseDto orderToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setMember(order.getMember());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setOrderCoffees(orderCoffeesToOrderCoffeeResponseDtos(order.getOrderCoffees()));
//        order.getOrderCoffees().stream().forEach(orderCoffee -> System.out.println(orderCoffee.getCoffee().getKorName()));
        return orderResponseDto;
    }

    default List<OrderCoffeeResponseDto> orderCoffeesToOrderCoffeeResponseDtos(
            List<OrderCoffee> orderCoffees) {
//        orderCoffees.stream().forEach(orderCoffee -> System.out.println(orderCoffee.getCoffee().getKorName()));
        return orderCoffees.stream()
                .map(orderCoffee -> OrderCoffeeResponseDto
                        .builder()
                        .coffeeId(orderCoffee.getCoffee().getCoffeeId())
                        .quantity(orderCoffee.getQuantity())
                        .korName(orderCoffee.getCoffee().getKorName())
                        .engName(orderCoffee.getCoffee().getEngName())
                        .price(orderCoffee.getCoffee().getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
