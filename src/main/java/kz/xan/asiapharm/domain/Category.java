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
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Good> goods;

    @ManyToOne
    @JoinColumn(name = "categoryType_id")
    private CategoryType categoryType;

    @Builder
    public Category(Long Id, String name, String description, CategoryType categoryType) {
        super(Id);
        this.name = name;
        this.description = description;
        this.categoryType = categoryType;
    }
}
