package kz.xan.asiapharm.services;

import kz.xan.asiapharm.commands.UserCommand;
import kz.xan.asiapharm.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends CrudService<User, Long>, UserDetailsService {
    Set<UserCommand> findAllCommands();
    UserCommand findUserCommandByUsername(String username);
}
