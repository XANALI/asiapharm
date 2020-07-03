package kz.xan.asiapharm.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "goods")
public class Good extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany
    @JoinTable(name = "good_category",
            joinColumns = @JoinColumn(name = "good_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "good")
    private Set<Order> orders;

    @Builder
    public Good(Long Id, String name, String description, Integer price, Integer quantity) {
        super(Id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
