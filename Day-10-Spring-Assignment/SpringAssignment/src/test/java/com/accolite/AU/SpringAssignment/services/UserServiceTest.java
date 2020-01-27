package com.accolite.AU.SpringAssignment.services;

import com.accolite.AU.SpringAssignment.services.UserService;
import com.accolite.AU.SpringAssignment.controllers.UserController;
import com.accolite.AU.SpringAssignment.models.User;
import com.accolite.AU.SpringAssignment.models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService service;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getOneUserTest() {
		User temp = new User();
		temp.setName("name1");
		temp.setId(1);
		temp.setAge(10);

		when(service.getUser(1)).thenReturn(temp);

		//testing
		User resultUser = userController.getUserById(1);
		assertEquals(temp.getId(),resultUser.getId());
		assertEquals(temp.getName(),resultUser.getName());
		assertEquals(temp.getAge(),resultUser.getAge());
	}

	@Test
	void getAllUsersTest() {
		Users temp = new Users();

		when(service.getAll()).thenReturn(temp);

		Users result = userController.getAll();

		//testing
		assertEquals(temp.getClass(),result.getClass());
	}

	@Test
	void addUserTest() {
		User temp = new User();
		temp.setName("name11");
		temp.setId(11);
		temp.setAge(51);

		doNothing().when(service).addUser(temp);

		//testing
		User resultUser = userController.addUser(temp);
		assertEquals(temp.getId(),resultUser.getId());
		assertEquals(temp.getName(),resultUser.getName());
		assertEquals(temp.getAge(),resultUser.getAge());
	}

	@Test
	void deleteUserTest() {
		User temp = new User();
		temp.setName("name11");
		temp.setId(11);
		temp.setAge(51);

		//DO-NOTHING AS VOID
		doNothing().when(service).deleteUser(temp);

		service.deleteUser(temp);

		//VERIFY
		verify(service).deleteUser(temp);
	}

}
