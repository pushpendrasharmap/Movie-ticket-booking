package com.mscomm.userservice.controller;

import com.mscomm.userservice.entity.User;
import com.mscomm.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

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

	    @PostMapping("/login")
	    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authHeader){
	        String encodedCredentials = authHeader.split(" ")[1];
			String[] decodedCredentials = new String(Base64.decodeBase64(encodedCredentials)).split(":");
			String email = decodedCredentials[0];
			String password = decodedCredentials[1];
			return ResponseEntity.ok(userService.getUserByEmailAndPassword(email, password));
	    }
}

