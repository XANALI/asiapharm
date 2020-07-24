package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.commands.CategoryCommand;
import kz.xan.asiapharm.converters.CategoryCommandToCategory;
import kz.xan.asiapharm.converters.CategoryToCategoryCommand;
import kz.xan.asiapharm.converters.CategoryTypeCommandToCategoryType;
import kz.xan.asiapharm.converters.CategoryTypeToCategoryTypeCommand;
import kz.xan.asiapharm.domain.Category;
import kz.xan.asiapharm.repositories.CategoryRepository;
import kz.xan.asiapharm.repositories.CategoryTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    CategoryServiceImpl categoryService;
    CategoryToCategoryCommand categoryToCategoryCommand;
    CategoryCommandToCategory categoryCommandToCategory;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryTypeRepository categoryTypeRepository;

    @Mock
    CategoryTypeToCategoryTypeCommand categoryTypeToCategoryTypeCommand;

    @Mock
    CategoryTypeCommandToCategoryType categoryTypeCommandToCategoryType;

    Category category;

    @BeforeEach
    void setUp() {
        category = Category.builder().Id(1L).build();
        categoryToCategoryCommand = new CategoryToCategoryCommand(categoryTypeToCategoryTypeCommand);
        categoryCommandToCategory = new CategoryCommandToCategory(categoryTypeCommandToCategoryType);
        categoryService = new CategoryServiceImpl(categoryRepository, categoryTypeRepository, categoryCommandToCategory, categoryToCategoryCommand);
    }

    @Test
    void findById() {
        when(categoryRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(category));

        Category returnedCat = categoryService.findById(category.getId());

        assertNotNull(returnedCat);
        verify(categoryRepository, times(1)).findById(anyLong());
    }

    @Test
    void findCommandById() {
        Optional<Category> categoryOptional = Optional.of(category);
        when(categoryRepository.findById(anyLong())).thenReturn(categoryOptional);

        CategoryCommand categoryCommand = categoryService.findCommandById(1L);

        assertNotNull(categoryCommand);
        verify(categoryRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        Category cat = Category.builder().Id(2L).build();
        when(categoryRepository.save(any())).thenReturn(category);

        Category returnedCat = categoryService.save(cat);

        assertNotNull(returnedCat);
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void saveCategoryCommand() {
        when(categoryRepository.save(any())).thenReturn(category);
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(1L);

        CategoryCommand saveCategoryCommand = categoryService.saveCategoryCommand(categoryCommand);
        assertNotNull(categoryCommand);
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void findAll() {
        Set<Category> categories = new HashSet<>();
        categories.add(Category.builder().Id(1L).build());
        categories.add(Category.builder().Id(2L).build());

        when(categoryRepository.findAll()).thenReturn(categories);

        Set<Category> returnedCategories = categoryService.findAll();

        assertNotNull(returnedCategories);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void delete() {
        categoryService.delete(category);

        verify(categoryRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        categoryService.deleteById(category.getId());

        verify(categoryRepository, times(1)).deleteById(anyLong());
    }
}