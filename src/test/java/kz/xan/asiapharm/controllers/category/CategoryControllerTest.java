package kz.xan.asiapharm.controllers.category;

import kz.xan.asiapharm.commands.CategoryCommand;
import kz.xan.asiapharm.domain.Category;
import kz.xan.asiapharm.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    @Mock
    CategoryService categoryService;

    CategoryController categoryController;

    Set<Category> categories;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        categoryController = new CategoryController(categoryService);

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

    @Test
    void getCategory() throws Exception {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(1L);
        categoryCommand.setName("alolo");

        when(categoryService.findCommandById(anyLong())).thenReturn(categoryCommand);

        mockMvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("category/singleInfo"))
                .andExpect(model().attribute("categoryCommand", categoryCommand));
    }

    @Test
    void changeCategory() throws Exception {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(1L);
        categoryCommand.setName("alolo");

        when(categoryService.saveCategoryCommand(any())).thenReturn(categoryCommand);

        mockMvc.perform(post("/category/changeCategory"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:show-all"));

        verify(categoryService, times(1)).saveCategoryCommand(any());
    }

    @Test
    void deleteCategory() throws Exception {

        mockMvc.perform(get("/category/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/category/show-all"));

        verify(categoryService, times(1)).deleteById(anyLong());
    }
}