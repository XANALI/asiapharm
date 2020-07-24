package kz.xan.asiapharm.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

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

    @ElementCollection(targetClass = ROLE.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(value = EnumType.STRING)
    private Set<ROLE> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Order> orders;

    @Builder
    public User(Long Id, String firstName, String lastName, String username, String password, String mobileNum, String email, LocalDate birthDate, Byte[] image, Set<ROLE> roles) {
        super(Id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.mobileNum = mobileNum;
        this.email = email;
        this.birthDate = birthDate;
        this.image = image;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
