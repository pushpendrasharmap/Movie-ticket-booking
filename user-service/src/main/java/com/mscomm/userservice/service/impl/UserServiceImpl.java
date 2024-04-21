package com.mscomm.userservice.service.impl;

import com.mscomm.userservice.entity.User;
import com.mscomm.userservice.repository.UserRepository;
import com.mscomm.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	 private static boolean isExecuting = false;
	    private final Lock lock = new ReentrantLock();
	    private UserRepository userRepository;

	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		Optional<User> user = userRepository.findByEmailAndPassword(email, password);
		if (user.isPresent()) {
			return user.get();
		}
		throw new NoSuchElementException("No User with given email and password is found");
	}


}
