package kz.xan.asiapharm.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "mobileNum")
    private String mobileNum;

    @Column(name = "email")
    private String email;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private ROLE role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Order> orders;

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
