package com.csye6220.ratingwebsitespringboot.Service.interfaces;

import com.csye6220.ratingwebsitespringboot.Entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    void saveUser(User user);

    void deleteUser(int id);
}
