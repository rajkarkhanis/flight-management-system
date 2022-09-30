package com.flights.controller;

import com.flights.bean.Airport;
import com.flights.bean.User;
import com.flights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userservice;

    @PostMapping("adduser")
    public User addUser(@RequestBody User user)	{
        User u = userservice.addUser(user);
        return u;
    }

    @GetMapping("getuser/{id}")
    public User viewUser(@PathVariable("id") int userId) {
        BigInteger bi = BigInteger.valueOf(userId);
        User u = userservice.viewUser(bi);
        return u;
    }

    @GetMapping("getallusers")
    public List<User> viewUser() {
        return userservice.viewUser();
    }
    @PutMapping("updateuser")
    public User updateUser(@RequestBody User user) {
        return userservice.updateUser(user);
    }

    @DeleteMapping("deleteuser/{id}")
    public void deleteUser(@PathVariable("id") int userId) {
        BigInteger bi = BigInteger.valueOf(userId);
        userservice.deleteUser(bi);
    }
}
