package com.accolite.AU.SpringAssignment.services;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import com.accolite.AU.SpringAssignment.controllers.UserController;
import com.accolite.AU.SpringAssignment.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.AU.SpringAssignment.models.User;
import com.accolite.AU.SpringAssignment.models.Users;

/**
 *  Using JAX-RS/JAXB to send and receive data in XML/JSON format.
 *  log4j logger is being used to log about the services being consumed.
 *
 * */

@Service
public class UserServiceImpl implements UserService {

	/** Logger initialized with the class-name */
	private static Logger logger = Logger.getLogger(UserController.class.getName());

	@Autowired
	private UserDaoImpl userDao;

	/** To get a single user with id. getUsers() returns a list of users.  */
	@Override
	public User getUser(int id) {
		logger.info("<CUSTOM LOG> Returning single user");
		return userDao.getUser(id);
	}

	/** Returns users, the container for all the users */
	@Override
	public Users getAll() {
		logger.info("<CUSTOM LOG> Returning all users");
		return userDao.getAll();
	}

	/** On receiving a POST request to add user, this method is invoked to add user. */
	@Override
	public void addUser(User user) {
		logger.info("<CUSTOM LOG> Adding user");
		//users.getUsers().add(user);
		userDao.addUser(user);
	}

	/** Invoked by controller to delete user on receiving DELETE request */
	@Override
	public void deleteUser(int id) {
		logger.info("<CUSTOM LOG> Deleting user");
		//users.getUsers().remove(user);
		userDao.deleteUser(id);
	}

	@Override
	public void editUser(User user) {
		userDao.editUser(user);
	}

	/** Setting up dummy data */
	@PostConstruct
	void setUp() {
		// PRE-SETUP
	}
}
