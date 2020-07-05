package kz.xan.asiapharm.controllers.category;

import kz.xan.asiapharm.domain.Category;
import kz.xan.asiapharm.services.CategoryService;
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
class CategoryControllerTest {
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    Set<Category> categories;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        categories = new HashSet<>();
        categories.add(Category.builder().Id(1L).build());
        categories.add(Category.builder().Id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void listCategories() throws Exception {
        when(categoryService.findAll()).thenReturn(categories);

        mockMvc.perform(get("/category/show-all"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories"))
                .andExpect(model().attribute("categories", hasSize(2)));
    }
}