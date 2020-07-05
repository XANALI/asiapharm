package kz.xan.asiapharm.controllers.good;

import kz.xan.asiapharm.domain.Good;
import kz.xan.asiapharm.services.GoodService;
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
class GoodControllerTest {
    @Mock
    GoodService goodService;

    @InjectMocks
    GoodController goodController;

    Set<Good> goods;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        goods = new HashSet<>();
        goods.add(Good.builder().Id(1L).build());
        goods.add(Good.builder().Id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(goodController).build();
    }

    @Test
    void listGoods() throws Exception {
        when(goodService.findAll()).thenReturn(goods);

        mockMvc.perform(get("/good/show-all"))
                .andExpect(status().isOk())
                .andExpect(view().name("goods"))
                .andExpect(model().attribute("goods", hasSize(2)));
    }
}