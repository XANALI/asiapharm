package kz.xan.asiapharm.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String mobileNum;
    private String email;
    private LocalDate birthDate;

    private Byte[] image;

    private ROLE role;

    @Builder
    public User(Long Id, String firstName, String lastName, String username, String password, String mobileNum, String email, LocalDate birthDate, Byte[] image, ROLE role) {
        super(Id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.mobileNum = mobileNum;
        this.email = email;
        this.birthDate = birthDate;
        this.image = image;
        this.role = role;
    }
}
