package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.domain.Good;
import kz.xan.asiapharm.repositories.GoodRepository;
import kz.xan.asiapharm.services.GoodService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GoodServiceImpl implements GoodService {
    private final GoodRepository goodRepository;

    public GoodServiceImpl(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    @Override
    public Good findById(Long aLong) {
        return goodRepository.findById(aLong).orElse(null);
    }

    @Override
    public Good save(Good object) {
        return goodRepository.save(object);
    }

    @Override
    public Set<Good> findAll() {
        Set<Good> goods = new HashSet<>();
        goodRepository.findAll().forEach(goods::add);

        return goods;
    }

    @Override
    public void delete(Good object) {
        goodRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        goodRepository.deleteById(aLong);
    }
}
