package com.codestates.order.controller;

import com.codestates.coffee.service.CoffeeService;
import com.codestates.dto.MultiResponseDto;
import com.codestates.dto.SingleResponseDto;
import com.codestates.order.dto.OrderPatchDto;
import com.codestates.order.dto.OrderPostDto;
import com.codestates.order.entity.Order;
import com.codestates.order.mapper.OrderMapper;
import com.codestates.order.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@RequestMapping("/v11/orders")
@Validated
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;
    private final CoffeeService coffeeService;

    public OrderController(OrderService orderService,
                           OrderMapper mapper,
                           CoffeeService coffeeService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.coffeeService = coffeeService;
    }

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDto));

        // TODO JPA 기능에 맞춰서 회원이 주문한 커피 정보를 ResponseEntity에 포함 필요
        // List<Coffee> coffees = coffeeService.findOrderedCoffees(order);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.orderToOrderResponseDto(order, null)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{order-id}")
    public ResponseEntity patchOrder(@PathVariable("order-id") @Positive long orderId,
                                     @Valid @RequestBody OrderPatchDto orderPatchDto) {
        orderPatchDto.setOrderId(orderId);
        Order order =
                orderService.updateOrder(mapper.orderPatchDtoToOrder(orderPatchDto));

        // TODO JPA 기능에 맞춰서 주문 상태 정보 업데이트 후, 주문한 커피 정보 포함 필요
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.orderToOrderResponseDto(order, null))
                , HttpStatus.OK);
    }
    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order order = orderService.findOrder(orderId);

        // TODO JPA 기능에 맞춰서 회원이 주문한 커피 정보를 ResponseEntity에 포함 필요
        // List<Coffee> coffees = coffeeService.findOrderedCoffees(order);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.orderToOrderResponseDto(order, null)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders(@Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        Page<Order> pageOrders = orderService.findOrders(page - 1, size);
        List<Order> orders = pageOrders.getContent();

        // TODO JPA 기능에 맞춰서 주문 목록에 회원이 주문한 커피 정보를 ResponseEntity에 포함 필요
       return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.ordersToOrderResponseDtos(orders), pageOrders),
                HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
