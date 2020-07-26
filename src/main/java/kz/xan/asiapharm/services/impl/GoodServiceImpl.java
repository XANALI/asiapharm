package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.commands.GoodCommand;
import kz.xan.asiapharm.converters.GoodCommandToGood;
import kz.xan.asiapharm.converters.GoodToGoodCommand;
import kz.xan.asiapharm.domain.Good;
import kz.xan.asiapharm.repositories.GoodRepository;
import kz.xan.asiapharm.services.GoodService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class GoodServiceImpl implements GoodService {
    private final GoodRepository goodRepository;
    private final GoodToGoodCommand goodToGoodCommand;
    private final GoodCommandToGood goodCommandToGood;

    public GoodServiceImpl(GoodRepository goodRepository, GoodToGoodCommand goodToGoodCommand, GoodCommandToGood goodCommandToGood) {
        this.goodRepository = goodRepository;
        this.goodToGoodCommand = goodToGoodCommand;
        this.goodCommandToGood = goodCommandToGood;
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

    @Override
    @Transactional
    public Set<GoodCommand> findAllCommands() {
        Set<GoodCommand> goodCommands = new HashSet<>();
        for(Good good : findAll()){
            goodCommands.add(goodToGoodCommand.convert(good));
        }
        return goodCommands;
    }

    @Override
    @Transactional
    public GoodCommand findCommandByID(Long id) {
        Good good = findById(id);
        if(good == null){
            throw new RuntimeException("Good By ID " + id + " is null!");
        }

        return goodToGoodCommand.convert(good);
    }

    @Override
    public Set<Good> findGoodsByNameContaining(String name) {
        Set<Good> goods = goodRepository.findGoodsByNameContainingIgnoreCase(name);
        if(goods.size() == 0){
            throw new RuntimeException("Goods not found");
        }

        return goods;
    }

    @Override
    @Transactional
    public Set<GoodCommand> findCommandsByNameContaining(String name) {
        Set<Good> goods = findGoodsByNameContaining(name);
        if(goods == null){
            throw new RuntimeException("Goods not found");
        }

        Set<GoodCommand> goodCommands = new HashSet<>();
        for(Good good : goods){
            goodCommands.add(goodToGoodCommand.convert(good));
        }

        return goodCommands;
    }
}
