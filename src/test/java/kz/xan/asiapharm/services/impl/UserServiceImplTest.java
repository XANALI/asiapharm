package kz.xan.asiapharm.services.impl;

import kz.xan.asiapharm.domain.User;
import kz.xan.asiapharm.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User returnedUser;

    @BeforeEach
    void setUp() {
        returnedUser = User.builder().Id(1L).firstName("Alikhan").build();
    }

    @Test
    void findById() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(returnedUser));

        User user1 = userService.findById(1L);
        assertEquals("Alikhan", user1.getFirstName());
        Mockito.verify(userRepository).findById(any(Long.class));
    }

    @Test
    void save() {
        when(userRepository.save(any(User.class))).thenReturn(returnedUser);

        User user = userService.save(returnedUser);

        assertNotNull(user);
        assertEquals(returnedUser.getFirstName(), user.getFirstName());
        verify(userRepository).save(any());
    }

    @Test
    void findAll() {
        Set<User> users = new HashSet<>();
        users.add(User.builder().firstName("Zhalgas").build());
        users.add(User.builder().firstName("Ali").build());

        when(userRepository.findAll()).thenReturn(users);

        Set<User> returnedUsers = userService.findAll();

        assertEquals(users.size(), returnedUsers.size());
        assertNotNull(returnedUsers);
        verify(userRepository).findAll();
    }

    @Test
    void delete() {
        userService.delete(returnedUser);

        verify(userRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        userService.deleteById(returnedUser.getId());

        verify(userRepository, times(1)).deleteById(any());
    }
}