package com.accolite.AU.SpringAssignment.services;

import com.accolite.AU.SpringAssignment.models.User;
import com.accolite.AU.SpringAssignment.models.Users;

import java.util.List;

public interface UserService {
    User getUser(int id);

    Users getAll();

    void addUser(User user);

    void deleteUser(int userId);

    void editUser(User user);
}
