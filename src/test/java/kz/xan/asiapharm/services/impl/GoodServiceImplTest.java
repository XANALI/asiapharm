package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.domain.Good;
import kz.xan.asiapharm.repositories.GoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GoodServiceImplTest {
    @Mock
    GoodRepository goodRepository;

    @InjectMocks
    GoodServiceImpl goodService;

    Good returnedGood;

    @BeforeEach
    void setUp() {
        returnedGood = Good.builder().Id(1L).name("Smth").build();
    }

    @Test
    void findById() {
        when(goodRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(returnedGood));

        Good good = goodService.findById(1L);

        assertEquals(returnedGood.getName(), good.getName());
        assertNotNull(good);

    }

    @Test
    void save() {
        Good good = Good.builder().Id(1L).build();
        when(goodRepository.save(any())).thenReturn(returnedGood);

        Good savedGood = goodService.save(good);

        assertNotNull(savedGood);
        verify(goodRepository, times(1)).save(any());
    }

    @Test
    void findAll() {
        Set<Good> goods = new HashSet<>();
        goods.add(Good.builder().Id(1L).build());
        goods.add(Good.builder().Id(2L).build());

        when(goodRepository.findAll()).thenReturn(goods);

        Set<Good> foundGoods = goodService.findAll();

        assertNotNull(foundGoods);
        assertEquals(goods.size(), foundGoods.size());
        verify(goodRepository, times(1)).findAll();
    }

    @Test
    void delete() {
        goodService.delete(returnedGood);

        verify(goodRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        goodService.deleteById(returnedGood.getId());

        verify(goodRepository, times(1)).deleteById(anyLong());
    }
}