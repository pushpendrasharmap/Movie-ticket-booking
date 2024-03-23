package com.mscomm.userservice.controller;

import com.mscomm.userservice.entity.User;
import com.mscomm.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser_ValidInput_ShouldReturnCreated() {
        // Arrange
        User user = User.builder()
                .email("test@example.com")
                .name("test")
                .password("password123")
                .build();

        when(userService.saveUser(user)).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.saveUser(user);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    void getUser_ValidEmail_ShouldReturnUser() {
        // Arrange
        String email = "test@example.com";
        User user = User.builder()
                .email(email)
                .name("test")
                .password("password123")
                .build();

        when(userService.getUserByEmailAndPassword(email)).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.getUser(email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserByEmailAndPassword(email);
    }
}