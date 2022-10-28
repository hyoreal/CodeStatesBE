package com.codestates.order.mapper;

import com.codestates.coffee.entity.Coffee;
import com.codestates.order.dto.ReadableOrderGroupDto;
import com.codestates.order.entity.CoffeeRef;
import com.codestates.coffee.service.CoffeeService;
import com.codestates.order.dto.OrderCoffeeResponseDto;
import com.codestates.order.entity.Order;
import com.codestates.order.dto.OrderPostDto;
import com.codestates.order.dto.OrderResponseDto;
import com.codestates.order.entity.ReadableOrderCoffee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        order.setMemberId(new AggregateReference.IdOnlyAggregateReference(orderPostDto.getMemberId()));
        List<CoffeeRef> orderCoffees =
                orderPostDto.getOrderCoffees()
                        .stream()
                        .map(orderCoffeeDto ->
                                new CoffeeRef(orderCoffeeDto.getCoffeeId(), orderCoffeeDto.getQuantity()))
                .collect(Collectors.toList());
        order.setOrderCoffees(orderCoffees);

        return order;
    }

    default OrderResponseDto orderToOrderResponseDto(CoffeeService coffeeService,
                                                     Order order) {

        long memberId = order.getMemberId().getId();

        List<OrderCoffeeResponseDto> orderCoffees =
                orderToOrderCoffeeResponseDto(coffeeService, order.getOrderCoffees());

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderCoffees(orderCoffees);
        orderResponseDto.setMemberId(memberId);
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());

        // TODO 주문에 대한 더 자세한 정보로의 변환은 요구 사항에 따라 다를 수 있습니다.

        return orderResponseDto;
    }

    default List<OrderCoffeeResponseDto> orderToOrderCoffeeResponseDto(
                                                        CoffeeService coffeeService,
                                                        List<CoffeeRef> orderCoffees) {
        return orderCoffees.stream()
                .map(coffeeRef -> {
                    Coffee coffee = coffeeService.findCoffee(coffeeRef.getCoffeeId());

                    return new OrderCoffeeResponseDto(coffee.getCoffeeId(),
                            coffee.getKorName(),
                            coffee.getEngName(),
                            coffee.getPrice(),
                            coffeeRef.getQuantity());
                }).collect(Collectors.toList());
    }

    default List<OrderResponseDto> readableOrderCoffeeToOrderResponseDto(List<ReadableOrderCoffee> orders) {
        Map<ReadableOrderGroupDto, List<ReadableOrderCoffee>> grouped =
                orders.stream().collect(
                        Collectors.groupingBy(readableOrderCoffee -> new ReadableOrderGroupDto(readableOrderCoffee)));

        List<OrderResponseDto> orderResponseDtos =
                grouped.entrySet().stream()
                        .map(e -> {
                            ReadableOrderGroupDto groupDto = e.getKey();
                            List<ReadableOrderCoffee> readableOrderCoffees = e.getValue();

                            OrderResponseDto orderResponseDto = new OrderResponseDto();
                            orderResponseDto.setOrderId(groupDto.getOrderId());
                            orderResponseDto.setMemberId(groupDto.getMemberId());
                            orderResponseDto.setOrderStatus(groupDto.getOrderStatus());
                            orderResponseDto.setCreatedAt(groupDto.getCreatedAt());

                            List<OrderCoffeeResponseDto> orderCoffeeResponseDtos =
                                    readableOrderCoffeeToOrderCoffeeResponseDto(readableOrderCoffees);

                            orderResponseDto.setOrderCoffees(orderCoffeeResponseDtos);

                            return orderResponseDto;
                        }).collect(Collectors.toList());

        // 최근 주문 순으로 정렬
        orderResponseDtos.sort(Comparator.comparing(OrderResponseDto::getOrderId).reversed());

        return orderResponseDtos;
    }

    default List<OrderCoffeeResponseDto> readableOrderCoffeeToOrderCoffeeResponseDto(
            List<ReadableOrderCoffee> readableOrderCoffees) {
        return readableOrderCoffees.stream()
                .map(readableOrderCoffee -> {
                    OrderCoffeeResponseDto orderCoffeeResponseDto =
                            new OrderCoffeeResponseDto(readableOrderCoffee.getCoffeeId(),
                                    readableOrderCoffee.getKorName(),
                                    readableOrderCoffee.getEngName(),
                                    readableOrderCoffee.getPrice(),
                                    readableOrderCoffee.getQuantity());
                    return orderCoffeeResponseDto;
                }).collect(Collectors.toList());
    }
}
