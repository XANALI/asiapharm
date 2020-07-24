package kz.xan.asiapharm.services;

import kz.xan.asiapharm.commands.CategoryCommand;
import kz.xan.asiapharm.domain.Category;

import java.util.Set;

public interface CategoryService extends CrudService<Category, Long> {

    CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand);
    CategoryCommand findCommandById(Long id);
    Set<CategoryCommand> findCategoryCommands();
}
