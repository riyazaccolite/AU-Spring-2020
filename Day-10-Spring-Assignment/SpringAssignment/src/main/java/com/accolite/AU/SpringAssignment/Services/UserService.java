package com.accolite.AU.SpringAssignment.Services;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import com.accolite.AU.SpringAssignment.controllers.UserController;
import org.springframework.stereotype.Service;

import com.accolite.AU.SpringAssignment.models.User;
import com.accolite.AU.SpringAssignment.models.Users;

@Service
public class UserService {
	
	Users users;
	private static Logger logger = Logger.getLogger(UserController.class.getName());

	public User getUser(int id) {
		logger.info("<CUSTOM LOG> Returning single user");
		return users.getUsers().stream().filter(user -> user.getId() == id).findFirst().get();
	}
	
	public Users getAll() {

		logger.info("<CUSTOM LOG> Returning all users");
		return users;
	}
	
	public void addUser(User user) {

		logger.info("<CUSTOM LOG> Adding user");
		users.getUsers().add(user);
	}
	
	public void deleteUser(User user) {
		logger.info("<CUSTOM LOG> Deleting user");
		users.getUsers().remove(user);
	}
	
	@PostConstruct
	void setUp() {
		users = new Users();
		users.setUsers(new ArrayList<>());
		User temp = new User();
		temp.setName("name1");
		temp.setId(1);
		temp.setAge(10);
		users.getUsers().add(temp);
		
		temp = new User();
		temp.setName("name2");
		temp.setId(2);
		temp.setAge(12);
		users.getUsers().add(temp);
		
		temp = new User();
		temp.setName("name3");
		temp.setId(3);
		temp.setAge(13);
		users.getUsers().add(temp);
	}
}
