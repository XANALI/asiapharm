package kz.xan.asiapharm.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Order extends BaseEntity {
    private User user;
    private Good good;
    private String description;

    @Builder
    public Order(Long Id, User user, Good good, String description) {
        super(Id);
        this.user = user;
        this.good = good;
        this.description = description;
    }
}
