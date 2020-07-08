package kz.xan.asiapharm.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCommand {
    private UserCommand user;
    private GoodCommand good;
    private String description;
}
