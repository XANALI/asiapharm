package kz.xan.asiapharm.converters;

import com.sun.istack.Nullable;
import kz.xan.asiapharm.commands.GoodCommand;
import kz.xan.asiapharm.domain.Good;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GoodToGoodCommand implements Converter<Good, GoodCommand> {

    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public GoodToGoodCommand(CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public GoodCommand convert(Good good) {
        if(good == null){
            return null;
        }

        final GoodCommand goodCommand = new GoodCommand();
        goodCommand.setId(good.getId());
        goodCommand.setName(good.getName());
        goodCommand.setDescription(good.getDescription());
        goodCommand.setPrice(good.getPrice());
        goodCommand.setQuantity(good.getQuantity());
        goodCommand.setCategory(categoryToCategoryCommand.convert(good.getCategory()));
        goodCommand.setImage(good.getImage());

        return goodCommand;
    }
}
