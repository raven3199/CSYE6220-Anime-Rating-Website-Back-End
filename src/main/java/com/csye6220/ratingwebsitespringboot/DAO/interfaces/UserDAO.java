package com.csye6220.ratingwebsitespringboot.DAO.interfaces;

import com.csye6220.ratingwebsitespringboot.Entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    void saveUser(User user);

    void deleteUser(int id);
}
