package kz.xan.asiapharm.commands;

import kz.xan.asiapharm.domain.CategoryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private String name;
    private String description;
    private CategoryType categoryType;
}
