package com.accolite.AU.SpringAssignment.dao;

import com.accolite.AU.SpringAssignment.controllers.UserController;
import com.accolite.AU.SpringAssignment.models.User;
import com.accolite.AU.SpringAssignment.models.Users;
import com.accolite.AU.SpringAssignment.rowmappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao {
    /**
     * Users is the wrapper class used to contain all users. Initialized in post-construct.
     * Useful when the data is to be sent in xml format.
     * */

    private final String insertQuery = "INSERT INTO userdata(name,age) VALUES(?, ?)";
    private final String updateQuery = "UPDATE userdata  SET name=?, age=? WHERE id=? LIMIT 1";

    /** Logger initialized with the class-name */
    private static Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** To get a single user with id. getUsers() returns a list of users.  */
    public User getUser(int id) {
        //logger.info("<CUSTOM LOG> Returning single user");
        //Optional<User> fetchedData = users.getUsers().stream().filter(user -> user.getId() == id).findFirst();
        //return fetchedData.orElse(null);

        return jdbcTemplate.queryForObject("select * from userdata where id=" + id, new UserRowMapper());
    }

    /** Returns users, the container for all the users */
    public Users getAll() {
        logger.info("<CUSTOM LOG> Returning all users");
        Users users = new Users();
        jdbcTemplate.query("select * from userdata", new UserRowMapper()).forEach(user -> users.getUsers().add(user));
        return users;
    }

    /** On receiving a POST request to add user, this method is invoked to add user. */
    public void addUser(User user) {
        logger.info("<CUSTOM LOG> Adding user");
        jdbcTemplate.update(insertQuery, user.getName(), user.getAge());
        //users.getUsers().add(user);
    }

    /** Invoked by controller to delete user on receiving DELETE request */
    public void deleteUser(int id) {
        logger.info("<CUSTOM LOG> Deleting user");
        jdbcTemplate.execute("DELETE FROM userdata WHERE id = " + id + " LIMIT 1");
        //users.getUsers().remove(user);
    }

    public void editUser(User user) {
        logger.info("<CUSTOM LOG> Editing user");
        jdbcTemplate.update(updateQuery, user.getName(),user.getAge(),user.getId());
    }

}
