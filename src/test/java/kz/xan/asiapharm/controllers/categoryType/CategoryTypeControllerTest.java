package kz.xan.asiapharm.controllers.categoryType;

import kz.xan.asiapharm.domain.CategoryType;
import kz.xan.asiapharm.services.CategoryTypeService;
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
class CategoryTypeControllerTest {

    @Mock
    CategoryTypeService categoryTypeService;

    @InjectMocks
    CategoryTypeController categoryTypeController;

    Set<CategoryType> categoryTypes;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        categoryTypes = new HashSet<>();
        categoryTypes.add(CategoryType.builder().Id(1L).build());
        categoryTypes.add(CategoryType.builder().Id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(categoryTypeController).build();
    }

    @Test
    void listCategoryTypes() throws Exception {
        when(categoryTypeService.findAll()).thenReturn(categoryTypes);

        mockMvc.perform(get("/category-type/show-all"))
                .andExpect(status().isOk())
                .andExpect(view().name("categoryTypes"))
                .andExpect(model().attribute("categoryTypes", hasSize(2)));
    }
}