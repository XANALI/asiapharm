package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.domain.Order;
import kz.xan.asiapharm.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    Order order;

    @BeforeEach
    void setUp() {
        order = Order.builder().Id(1L).build();
    }

    @Test
    void findById() {
        when(orderRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(order));

        Order returnedOrder = orderService.findById(order.getId());

        assertNotNull(returnedOrder);
        verify(orderRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        Order saveOrder = Order.builder().Id(2L).build();
        when(orderRepository.save(any())).thenReturn(order);

        Order returnedOrder = orderService.save(saveOrder);

        assertNotNull(returnedOrder);
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void findAll() {
        Set<Order> orders = new HashSet<>();
        orders.add(Order.builder().Id(1L).build());
        orders.add(Order.builder().Id(2L).build());

        when(orderRepository.findAll()).thenReturn(orders);

        Set<Order> returnedOrders = orderService.findAll();

        assertNotNull(returnedOrders);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void delete() {
        orderService.delete(order);

        verify(orderRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        orderService.deleteById(order.getId());

        verify(orderRepository, times(1)).deleteById(anyLong());
    }
}