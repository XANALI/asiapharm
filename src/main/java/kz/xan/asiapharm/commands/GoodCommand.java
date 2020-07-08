package kz.xan.asiapharm.commands;

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
    private CategoryCommand category;
}
