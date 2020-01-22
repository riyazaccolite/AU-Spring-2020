package com.accolite.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import com.accolite.web.models.User;

@Controller
@RequestMapping("api")
public class FirstController {
	
	private List<User> users;
	
//	public FirstController() {
//		users = new ArrayList<User>();
//		User tempUser = new User();
//		tempUser.setName("name1");
//		users.add(tempUser);
//		tempUser = new User();
//		tempUser.setName("name2");
//		users.add(tempUser);
//		tempUser = new User();
//		tempUser.setName("name3");
//		users.add(tempUser);
//	}
	
	@RequestMapping(value="hi", method = RequestMethod.GET)
	public @ResponseBody String sayHi() {
		return "Hello. Welcome to first controller";
	}
	
	@RequestMapping(value="bye")
	public @ResponseBody String sayBye() {
		return "Bye";
	}
	
	@RequestMapping("user")
	public @ResponseBody User sendUser() {
		return new User();
	}
	
	@RequestMapping(value="anotherUser", method = RequestMethod.POST)
	public @ResponseBody User getAndSendUser(@RequestBody User user) {
		return user;
	}
	
	@RequestMapping("employees")
	public String getEmployeesPage()
	{
		return "EmployeeList";
	}
	
//	@RequestMapping("findUser")
//	public @ResponseBody User getUserById(@RequestParam String userName) {
//		users = new ArrayList<User>();
//		User tempUser = new User();
//		tempUser.setName("name1");
//		users.add(tempUser);
//		tempUser = new User();
//		tempUser.setName("name2");
//		users.add(tempUser);
//		tempUser = new User();
//		tempUser.setName("name3");
//		users.add(tempUser);
//		return users.stream().filter( user -> user.getName().equals(userName)).findFirst().get();
//	}
}
