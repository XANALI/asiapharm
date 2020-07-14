package kz.xan.asiapharm.services;

import kz.xan.asiapharm.commands.CategoryCommand;
import kz.xan.asiapharm.domain.Category;

public interface CategoryService extends CrudService<Category, Long> {

    CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand);
}
