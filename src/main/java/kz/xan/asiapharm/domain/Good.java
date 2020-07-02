package kz.xan.asiapharm.domain;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Good {
    private String name;
    private String description;
    private Integer price;
    private Integer quantity;

    private Set<Category> categories;
}
