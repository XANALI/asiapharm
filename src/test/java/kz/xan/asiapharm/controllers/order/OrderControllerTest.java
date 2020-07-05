package kz.xan.asiapharm.controllers.order;

import kz.xan.asiapharm.domain.Order;
import kz.xan.asiapharm.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    Set<Order> orders;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        orders = new HashSet<>();
        orders.add(Order.builder().Id(1L).build());
        orders.add(Order.builder().Id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void listOrders() throws Exception {
        when(orderService.findAll()).thenReturn(orders);

        mockMvc.perform(get("/order/show-all"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders"))
                .andExpect(model().attribute("orders", hasSize(2)));
    }
}