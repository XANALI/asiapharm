package kz.xan.asiapharm.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryTypeCommand extends BaseCommand {
    private String name;
    private String description;
}
