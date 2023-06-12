package com.bbva.authentication.infrastructure.controllers;

import com.bbva.authentication.infrastructure.entities.UserEntity;
import com.bbva.authentication.infrastructure.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
public class LoginController {
	
	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	
	@PostMapping(value = "/user/create")
	public void create(@RequestParam String username, @RequestParam String password) {
		userRepositoryImpl.save(new UserEntity(username, password));
	}
	
	@GetMapping(value = "/user/{username}")
	public ResponseEntity<UserEntity> getByUserName(@PathVariable String username) {
		UserEntity user = userRepositoryImpl.findByUsername(username);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping(value = "/user/authenticate-basic")
	public ResponseEntity<Boolean> authenticate(@RequestParam String username, @RequestParam String password) {
		Boolean authenticated = userRepositoryImpl.authenticateUser(username, password);
		return ResponseEntity.ok(authenticated);
	}
	
	@PostMapping(value = "/user/authenticate-form")
	public ResponseEntity<?> authenticateForm(@RequestParam String username, @RequestParam String password) {
		if (userRepositoryImpl.authenticateUser(username, password)) {
			return ResponseEntity.ok("home");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			                     .build();
		}
	}
}
