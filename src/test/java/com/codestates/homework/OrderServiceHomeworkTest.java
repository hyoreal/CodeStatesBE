package com.codestates.homework;

import com.codestates.exception.BusinessLogicException;
import com.codestates.helper.StubData;
import com.codestates.order.entity.Order;
import com.codestates.order.repository.OrderRepository;
import com.codestates.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class OrderServiceHomeworkTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void cancelOrderTest() {
        // given
        long orderId = 1L;
        Order order = StubData.MockOrder.getSingleResponseBody(orderId);

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // when / then
        assertThrows(BusinessLogicException.class, () -> orderService.cancelOrder(order.getOrderId()));
    }
}
