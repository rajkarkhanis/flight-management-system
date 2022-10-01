package com.flights.controller;

import com.flights.bean.User;
import com.flights.exception.RecordNotFound;
import com.flights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class UserDemoController {
    @Autowired
    private UserService userservice;

    @PostMapping("adduser")
    public User addUser(@RequestBody User user) throws Throwable{
        User u = userservice.addUser(user);
        return u;
    }

    @GetMapping("getuser/{id}")
    public User viewUser(@PathVariable("id") int userId) throws RecordNotFound {
        BigInteger bi = BigInteger.valueOf(userId);
        User u = userservice.viewUser(bi);
        return u;
    }

    @GetMapping("getallusers")
    public List<User> viewUser() {
        return userservice.viewUser();
    }
    @PutMapping("updateuser")
    public User updateUser(@RequestBody User user) throws Throwable {
        return userservice.updateUser(user);
    }

    @DeleteMapping("deleteuser/{id}")
    public void deleteUser(@PathVariable("id") int userId) throws RecordNotFound{
        BigInteger bi = BigInteger.valueOf(userId);
        userservice.deleteUser(bi);
    }
}
