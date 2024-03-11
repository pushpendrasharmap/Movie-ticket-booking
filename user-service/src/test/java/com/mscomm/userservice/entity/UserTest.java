package com.mscomm.userservice.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void testUserEntity() {
        // Create a user instance
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setPassword("password");
        user.setEmail("john.doe@example.com");

        // Test the getters
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("John Doe");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getEmail()).isEqualTo("john.doe@example.com");

        // Test the setters
        user.setId(2L);
        user.setName("Jane Smith");
        user.setPassword("newpassword");
        user.setEmail("jane.smith@example.com");

        assertThat(user.getId()).isEqualTo(2L);
        assertThat(user.getName()).isEqualTo("Jane Smith");
        assertThat(user.getPassword()).isEqualTo("newpassword");
        assertThat(user.getEmail()).isEqualTo("jane.smith@example.com");
    }
}
