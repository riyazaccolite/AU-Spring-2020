package com.accolite.AU.SpringAssignment.Services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.accolite.AU.SpringAssignment.models.User;
import com.accolite.AU.SpringAssignment.models.Users;

@Service
public class UserService {
	
	Users users;
	
	public User getUser(int id) {
		return users.getUsers().stream().filter(user -> user.getId() == id).findFirst().get();
	}
	
	public Users getAll() {
		return users;
	}
	
	public void addUser(User user) {
		users.getUsers().add(user);
	}
	
	public void deleteUser(User user) {
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
