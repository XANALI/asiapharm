package kz.xan.asiapharm.repositories;

import kz.xan.asiapharm.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
