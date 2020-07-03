package kz.xan.asiapharm.repositories;

import kz.xan.asiapharm.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
