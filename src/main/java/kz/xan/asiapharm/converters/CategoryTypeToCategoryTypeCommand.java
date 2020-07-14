package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.CategoryTypeCommand;
import kz.xan.asiapharm.domain.CategoryType;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryTypeToCategoryTypeCommand implements Converter<CategoryType, CategoryTypeCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryTypeCommand convert(CategoryType categoryType) {
        if(categoryType == null){
            return null;
        }

        final CategoryTypeCommand categoryTypeCommand = new CategoryTypeCommand();
        categoryTypeCommand.setId(categoryType.getId());
        categoryTypeCommand.setName(categoryType.getName());
        categoryTypeCommand.setDescription(categoryType.getDescription());

        return categoryTypeCommand;
    }
}
