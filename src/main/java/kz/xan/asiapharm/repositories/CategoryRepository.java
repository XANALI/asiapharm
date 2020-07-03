package kz.xan.asiapharm.repositories;

import kz.xan.asiapharm.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
