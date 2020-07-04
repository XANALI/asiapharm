package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.domain.CategoryType;
import kz.xan.asiapharm.repositories.CategoryTypeRepository;
import kz.xan.asiapharm.services.CategoryTypeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryTypeServiceImpl implements CategoryTypeService {

    private final CategoryTypeRepository categoryTypeRepository;

    public CategoryTypeServiceImpl(CategoryTypeRepository categoryTypeRepository) {
        this.categoryTypeRepository = categoryTypeRepository;
    }

    @Override
    public CategoryType findById(Long aLong) {
        return categoryTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public CategoryType save(CategoryType object) {
        return categoryTypeRepository.save(object);
    }

    @Override
    public Set<CategoryType> findAll() {
        Set<CategoryType> categoryTypes = new HashSet<>();
        categoryTypeRepository.findAll().forEach(categoryTypes::add);

        return categoryTypes;
    }

    @Override
    public void delete(CategoryType object) {
        categoryTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        categoryTypeRepository.deleteById(aLong);
    }
}
