package kz.xan.asiapharm.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand extends BaseCommand {
    private String name;
    private String description;
    private CategoryTypeCommand categoryType;
}
