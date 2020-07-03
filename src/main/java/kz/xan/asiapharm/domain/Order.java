package kz.xan.asiapharm.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    @Column(name = "description")
    private String description;

    @Builder
    public Order(Long Id, User user, Good good, String description) {
        super(Id);
        this.user = user;
        this.good = good;
        this.description = description;
    }
}
