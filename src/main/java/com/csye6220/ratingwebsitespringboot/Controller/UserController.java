package com.csye6220.ratingwebsitespringboot.Controller;

import com.csye6220.ratingwebsitespringboot.Entity.User;
import com.csye6220.ratingwebsitespringboot.Service.interfaces.UserService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, String> userLogin(@RequestBody User loginUser) {
        User user = null;
        Map<String, String> response = new HashMap<>();
        try {
            user = userService.getUserByUsername(loginUser.getUsername());
        } catch (NoResultException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

        if(user == null) {
            System.out.println("User does not exist");
            response.put("result", "not exist");
        } else {
            if (!user.getPassword().equals(loginUser.getPassword())) {
                System.out.println("Password incorrect");
                response.put("result", "password incorrect");
            } else {
                System.out.println("Success!");
                response.put("result", "success");
                response.put("id", String.valueOf(user.getId()));
            }
        }
        return response;
    }

    @PostMapping("/register")
    public Map<String, String> userRegister(@RequestBody User registerUser) {
        User user = null;
        Map<String, String> response = new HashMap<>();
        try {
            user = userService.getUserByUsername(registerUser.getUsername());
        } catch (NoResultException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

        if(user != null) {
            System.out.println("Username already exist");
            response.put("result", "already exist");
        } else {
            userService.saveUser(registerUser);
            response.put("result", "success");
        }
        return response;
    }
}
