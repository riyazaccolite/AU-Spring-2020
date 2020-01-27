package com.accolite.AU.SpringAssignment.controllers;

import org.springframework.web.bind.annotation.*;

import com.accolite.AU.SpringAssignment.services.UserService;
import com.accolite.AU.SpringAssignment.models.User;
import com.accolite.AU.SpringAssignment.models.Users;

@RestController
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	

	@GetMapping(path = "user/{id}", produces="application/xml")
	public User getUserById(@PathVariable int id) {
		return userService.getUser(id);
	}
	
	@GetMapping(path= "user", produces = "application/xml")
	public Users getAll() {
		return userService.getAll();
	}
	
	@PostMapping(path = "add", consumes = "application/xml")
	public User addUser(@RequestBody User user) {
		userService.addUser(user);
		return user;
	}
	
	@DeleteMapping(path = "delete", consumes = "application/xml")
	public void deleteUser(@RequestBody User user) {
		userService.deleteUser(user);
	}
	
}
