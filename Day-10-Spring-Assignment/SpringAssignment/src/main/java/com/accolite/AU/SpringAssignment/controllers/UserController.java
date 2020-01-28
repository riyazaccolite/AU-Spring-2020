package com.accolite.AU.SpringAssignment.controllers;

import com.accolite.AU.SpringAssignment.services.UserService;
import org.springframework.web.bind.annotation.*;

import com.accolite.AU.SpringAssignment.models.User;
import com.accolite.AU.SpringAssignment.models.Users;


@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userServiceImpl;

	public UserController(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}
	

	@GetMapping(path = "user/{id}", produces= {"application/json", "application/xml"})
	public User getUserById(@PathVariable int id) {
		return userServiceImpl.getUser(id);
	}
	
	@GetMapping(path= "user", produces = {"application/json", "application/xml"})
	public Users getAll() {
		return userServiceImpl.getAll();
	}
	
	@PostMapping(path = "add", consumes = {"application/xml", "application/json"})
	public User addUser(@RequestBody User user) {
		userServiceImpl.addUser(user);
		return user;
	}
	
	@DeleteMapping(path = "delete/{id}")
	public void deleteUser(@PathVariable int id) {
		userServiceImpl.deleteUser(id);
	}

	@PutMapping(path = "edit", consumes = {"application/xml", "application/json"})
	public void editUser(@RequestBody User user) {
		userServiceImpl.editUser(user);
	}
	
}
