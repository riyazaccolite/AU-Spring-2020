package com.accolite.web.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.web.demo.models.User;
import com.accolite.web.demo.services.UserService;

@RestController

public class SecondController {

	private final UserService userService;
	
	SecondController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("user/{username}/{something}")
	public User getUserByName(@PathVariable(value="username") String name, @PathVariable int something) {
		return userService.getUser(name);
	}
	

	@GetMapping("checkaspect")
	public String doSomething() {
		return userService.something();
	}
	
//	List<User> users;
//	
//	@GetMapping("user")
//	public User getUserByName(@RequestParam String name){
//		return users.stream().filter(user -> user.getName().equals(name)).findFirst().get();
//	}
//	
//	SecondController() {
//		users = new ArrayList<>();
//		User u = new User();
//		u.setName("tempname");
//		users.add(u);
//	}
	

}
