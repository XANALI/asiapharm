package kz.xan.asiapharm.commands;

import kz.xan.asiapharm.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodCommand {
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;
    private Category category;
}
