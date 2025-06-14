package com.tharbad.tharbad_api.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tharbad.model.User;
import com.tharbad.repository.UserRepository;
import com.tharbad.service.CustomUserDetailsService;

public class CustomUserDetailsServiceTest {

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("123456");
        user.setRole("USER");

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(user));

        CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService(userRepository);

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("unknown@test.com");
        });
    }
}
