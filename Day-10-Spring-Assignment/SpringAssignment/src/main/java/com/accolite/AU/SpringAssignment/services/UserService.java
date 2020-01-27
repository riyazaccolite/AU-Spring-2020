package com.accolite.AU.SpringAssignment.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import com.accolite.AU.SpringAssignment.controllers.UserController;
import org.springframework.stereotype.Service;

import com.accolite.AU.SpringAssignment.models.User;
import com.accolite.AU.SpringAssignment.models.Users;

/**
 *  Using JAX-RS/JAXB to send and receive data in XML/JSON format.
 *  log4j logger is being used to log about the services being consumed.
 *
 * */

@Service
public class UserService {

	/**
	 * Users is the wrapper class used to contain all users. Initialized in post-construct.
	 * Useful when the data is to be sent in xml format.
	 * */
	Users users;

	/** Logger initialized with the class-name */
	private static Logger logger = Logger.getLogger(UserController.class.getName());

	/** To get a single user with id. getUsers() returns a list of users.  */
	public User getUser(int id) {
		logger.info("<CUSTOM LOG> Returning single user");
		Optional<User> fetchedData = users.getUsers().stream().filter(user -> user.getId() == id).findFirst();
		return fetchedData.orElse(null);
	}

	/** Returns users, the container for all the users */
	public Users getAll() {
		logger.info("<CUSTOM LOG> Returning all users");
		return users;
	}

	/** On receiving a POST request to add user, this method is invoked to add user. */
	public void addUser(User user) {
		logger.info("<CUSTOM LOG> Adding user");
		users.getUsers().add(user);
	}

	/** Invoked by controller to delete user on receiving DELETE request */
	public void deleteUser(User user) {
		logger.info("<CUSTOM LOG> Deleting user");
		users.getUsers().remove(user);
	}

	/** Setting up dummy data */
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
