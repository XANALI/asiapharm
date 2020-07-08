package kz.xan.asiapharm.commands;

import kz.xan.asiapharm.domain.ROLE;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mobileNum;
    private String email;
    private LocalDate birthDate;
    private ROLE role;
}
