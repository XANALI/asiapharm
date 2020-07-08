package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.GoodCommand;
import kz.xan.asiapharm.domain.Good;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GoodCommandToGood implements Converter<GoodCommand, Good> {

    private final CategoryCommandToCategory categoryCommandToCategory;

    public GoodCommandToGood(CategoryCommandToCategory categoryCommandToCategory) {
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Synchronized
    @Nullable
    @Override
    public Good convert(GoodCommand goodCommand) {
        if(goodCommand == null){
            return null;
        }

        final Good good = new Good();
        good.setName(goodCommand.getName());
        good.setDescription(goodCommand.getDescription());
        good.setPrice(goodCommand.getPrice());
        good.setQuantity(goodCommand.getQuantity());
        good.setCategory(categoryCommandToCategory.convert(goodCommand.getCategory()));

        return good;
    }
}
