package kz.xan.asiapharm.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Good extends BaseEntity {
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;

    private Set<Category> categories;

    public Good(Long Id, String name, String description, Integer price, Integer quantity) {
        super(Id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
