package kz.xan.asiapharm.repositories;

import kz.xan.asiapharm.domain.Good;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface GoodRepository extends CrudRepository<Good, Long> {
    Set<Good> findGoodsByNameContainingIgnoreCase(String name);
}
