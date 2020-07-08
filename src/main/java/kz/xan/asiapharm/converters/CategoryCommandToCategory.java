package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.CategoryCommand;
import kz.xan.asiapharm.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    private final CategoryTypeCommandToCategoryType categoryTypeCommandToCategoryType;

    public CategoryCommandToCategory(CategoryTypeCommandToCategoryType categoryTypeCommandToCategoryType) {
        this.categoryTypeCommandToCategoryType = categoryTypeCommandToCategoryType;
    }

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null){
            return null;
        }

        final Category category = new Category();
        category.setName(categoryCommand.getName());
        category.setDescription(categoryCommand.getDescription());
        category.setCategoryType(categoryTypeCommandToCategoryType.convert(categoryCommand.getCategoryType()));

        return category;
    }
}
