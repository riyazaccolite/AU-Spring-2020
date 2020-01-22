package com.accolite.web.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accolite.web.demo.models.User;

@Component //OR @SERVICE
public class UserService {
	List<User> users;
	
	@GetMapping("user")
	public User getUser(@RequestParam String name){
		return users.stream().filter(user -> user.getName().equals(name)).findFirst().get();
	}
	
	public String something() {
		return "HELLO";
	}
	
	
	@PostConstruct
	void setUp() {
		users = new ArrayList<>();
		User u = new User();
		u.setName("tempname");
		users.add(u);
	}
}
