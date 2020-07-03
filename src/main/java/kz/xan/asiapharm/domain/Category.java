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
public class Category extends BaseEntity {
    private String name;
    private String description;

    @Builder
    public Category(Long Id, String name, String description) {
        super(Id);
        this.name = name;
        this.description = description;
    }
}
