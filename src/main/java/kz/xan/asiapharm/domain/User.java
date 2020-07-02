package kz.xan.asiapharm.domain;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mobileNum;
    private String email;
    private LocalDate birthDate;

    private Byte[] image;

    private ROLE role;
}
