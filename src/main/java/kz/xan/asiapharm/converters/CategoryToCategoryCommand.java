package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.CategoryCommand;
import kz.xan.asiapharm.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    private final CategoryTypeToCategoryTypeCommand categoryTypeToCategoryTypeCommand;

    public CategoryToCategoryCommand(CategoryTypeToCategoryTypeCommand categoryTypeToCategoryTypeCommand) {
        this.categoryTypeToCategoryTypeCommand = categoryTypeToCategoryTypeCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if(category == null){
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setName(category.getName());
        categoryCommand.setDescription(category.getDescription());
        categoryCommand.setCategoryType(categoryTypeToCategoryTypeCommand.convert(category.getCategoryType()));

        return categoryCommand;
    }
}
