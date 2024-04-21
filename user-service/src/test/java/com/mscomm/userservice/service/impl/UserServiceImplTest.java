package com.mscomm.userservice.service.impl;

import com.mscomm.userservice.entity.User;
import com.mscomm.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser_ValidUser_ShouldReturnSavedUser() {
        // Arrange
        User user = User.builder()
                .email("test@example.com")
                .name("test")
                .password("password123")
                .build();;
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User savedUser = userService.saveUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserByEmailAndPassword_ValidEmail_ShouldReturnUser() {
        // Arrange
        String email = "test@example.com";
        User user = User.builder()
                .email(email)
                .name("test")
                .password("password123")
                .build();
        when(userRepository.findByEmailAndPassword(email, user.getPassword())).thenReturn(Optional.of(user));

        // Act
        User foundUser = userService.getUserByEmailAndPassword(email, user.getPassword());

        // Assert
        assertNotNull(foundUser);
        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findByEmailAndPassword(email, user.getPassword());
    }

    @Test
    void getUserByEmailAndPassword_NonExistingEmail_ShouldThrowNoSuchElementException() {
        // Arrange
        String email = "nonexisting@example.com";
        String password = "test";
        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> userService.getUserByEmailAndPassword(email, password));
        verify(userRepository, times(1)).findByEmailAndPassword(email, password);
    }
}