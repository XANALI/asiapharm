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
@Table(name = "categoryTypes")
public class CategoryType extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryType")
    private Set<Category> categories;

    @Builder
    public CategoryType(Long Id, String name, String description) {
        super(Id);
        this.name = name;
        this.description = description;
    }
}
