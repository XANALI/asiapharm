package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.commands.UserCommand;
import kz.xan.asiapharm.converters.UserToUserCommand;
import kz.xan.asiapharm.domain.User;
import kz.xan.asiapharm.repositories.UserRepository;
import kz.xan.asiapharm.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserToUserCommand toUserCommand;

    public UserServiceImpl(UserRepository userRepository, UserToUserCommand toUserCommand) {
        this.userRepository = userRepository;
        this.toUserCommand = toUserCommand;
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElse(null);
        if(user == null){
            throw new UsernameNotFoundException("Username " + s + " not Found!");
        }

        return user;
    }

    @Override
    @Transactional
    public Set<UserCommand> findAllCommands() {
        Set<User> users = findAll();
        Set<UserCommand> userCommands = new HashSet<>();
        for(User user : users){
            userCommands.add(toUserCommand.convert(user));
        }

        return userCommands;
    }

    @Override
    @Transactional
    public UserCommand findUserCommandByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null){
            throw new RuntimeException("User not found");
        }

        return toUserCommand.convert(user);
    }
}
