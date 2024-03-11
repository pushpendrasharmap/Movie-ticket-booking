package com.mscomm.userservice.service;

import com.mscomm.userservice.entity.User;
public interface UserService {
	User saveUser(User user);
    User getUserByEmailAndPassword(String username);
}
