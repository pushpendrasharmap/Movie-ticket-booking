package com.mscomm.userservice.controller;

import com.mscomm.userservice.entity.User;
import com.mscomm.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
@CrossOrigin(origins="*")
public class UserController {
	  private UserService userService;

	    @PostMapping
	    public ResponseEntity<User> saveUser(@RequestBody User user){
	        User savedUser = userService.saveUser(user);
	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    }

	    @GetMapping("/{email}")
	    public ResponseEntity<User> getUser(@PathVariable("email") String email){
	        User user = userService.getUserByEmailAndPassword(email);
	        return ResponseEntity.ok(user);
	    }
}

