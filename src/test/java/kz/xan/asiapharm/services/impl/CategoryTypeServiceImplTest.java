package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.domain.CategoryType;
import kz.xan.asiapharm.repositories.CategoryTypeRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryTypeServiceImplTest {
    @Mock
    CategoryTypeRepository categoryTypeRepository;

    @InjectMocks
    CategoryTypeServiceImpl categoryTypeService;

    CategoryType categoryType;

    @BeforeEach
    void setUp() {
        categoryType = CategoryType.builder().Id(1L).build();
    }

    @Test
    void findById() {
        when(categoryTypeRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(categoryType));

        CategoryType returnedCatType = categoryTypeService.findById(categoryType.getId());

        assertNotNull(returnedCatType);
        verify(categoryTypeRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        CategoryType catType = CategoryType.builder().Id(2L).build();
        when(categoryTypeRepository.save(any())).thenReturn(categoryType);

        CategoryType returnedCatType = categoryTypeService.save(catType);

        assertNotNull(returnedCatType);
        verify(categoryTypeRepository, times(1)).save(any());
    }

    @Test
    void findAll() {
        Set<CategoryType> categoryTypes = new HashSet<>();
        categoryTypes.add(CategoryType.builder().Id(1L).build());
        categoryTypes.add(CategoryType.builder().Id(2L).build());

        when(categoryTypeRepository.findAll()).thenReturn(categoryTypes);

        Set<CategoryType> retCategoryTypes = categoryTypeService.findAll();

        assertNotNull(retCategoryTypes);
        verify(categoryTypeRepository, times(1)).findAll();
    }

    @Test
    void delete() {
        categoryTypeService.delete(categoryType);

        verify(categoryTypeRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        categoryTypeService.deleteById(categoryType.getId());

        verify(categoryTypeRepository, times(1)).deleteById(anyLong());
    }
}