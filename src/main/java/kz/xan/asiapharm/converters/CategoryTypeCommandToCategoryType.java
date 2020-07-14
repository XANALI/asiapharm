package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.CategoryTypeCommand;
import kz.xan.asiapharm.domain.CategoryType;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryTypeCommandToCategoryType implements Converter<CategoryTypeCommand, CategoryType> {

    @Synchronized
    @Nullable
    @Override
    public CategoryType convert(CategoryTypeCommand categoryTypeCommand) {
        if(categoryTypeCommand == null){
            return null;
        }

        final CategoryType categoryType = new CategoryType();
        categoryType.setId(categoryTypeCommand.getId());
        categoryType.setName(categoryTypeCommand.getName());
        categoryType.setDescription(categoryTypeCommand.getDescription());

        return categoryType;
    }
}
