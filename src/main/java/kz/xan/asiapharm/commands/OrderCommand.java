package kz.xan.asiapharm.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCommand extends BaseCommand {
    private UserCommand user;
    private GoodCommand good;
    private String description;
}
