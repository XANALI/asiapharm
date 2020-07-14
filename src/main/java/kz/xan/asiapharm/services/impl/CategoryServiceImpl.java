package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.commands.CategoryCommand;
import kz.xan.asiapharm.converters.CategoryCommandToCategory;
import kz.xan.asiapharm.converters.CategoryToCategoryCommand;
import kz.xan.asiapharm.domain.Category;
import kz.xan.asiapharm.repositories.CategoryRepository;
import kz.xan.asiapharm.repositories.CategoryTypeRepository;
import kz.xan.asiapharm.services.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryTypeRepository categoryTypeRepository;
    private final CategoryCommandToCategory fromCommandConverter;
    private final CategoryToCategoryCommand toCommandConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryTypeRepository categoryTypeRepository, CategoryCommandToCategory fromCommandConverter, CategoryToCategoryCommand toCommandConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryTypeRepository = categoryTypeRepository;
        this.fromCommandConverter = fromCommandConverter;
        this.toCommandConverter = toCommandConverter;
    }

    @Override
    public Category findById(Long aLong) {
        return categoryRepository.findById(aLong).orElse(null);
    }

    @Override
    public Category save(Category object) {
        return categoryRepository.save(object);
    }

    @Override
    public Set<Category> findAll() {
        Set<Category> categories = new HashSet<>();
        categoryRepository.findAll().forEach(categories::add);

        return categories;
    }

    @Override
    public void delete(Category object) {
        categoryRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        categoryRepository.deleteById(aLong);
    }

    @Override
    @Transactional
    public CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand) {
        Category category = fromCommandConverter.convert(categoryCommand);
        assert category != null;
        category.setCategoryType(categoryTypeRepository.save(category.getCategoryType()));
        Category savedCategory = categoryRepository.save(category);

        return toCommandConverter.convert(savedCategory);
    }
}
