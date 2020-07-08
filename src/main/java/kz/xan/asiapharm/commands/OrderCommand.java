package kz.xan.asiapharm.commands;

import kz.xan.asiapharm.domain.Good;
import kz.xan.asiapharm.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCommand {
    private User user;
    private Good good;
    private String description;
}
