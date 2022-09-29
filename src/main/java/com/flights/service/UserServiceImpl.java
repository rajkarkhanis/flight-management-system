package com.flights.service;

import com.flights.bean.User;
import com.flights.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    UserDao repo;

    @Override
    public User addUser(User user) {
        repo.save(user);
        return user;
    }

    @Override
    public User viewUser(BigInteger userId) {
        int id = userId.intValue(); //convert BigInteger to integer
        Optional u = repo.findById(id);
		User u1= (User) u.get();
		return u1;
    }

    @Override
    public List<User> viewUser() {
        List<User> user = repo.findAll();
		return user;
    }

    @Override
    public User updateUser(User user) {
        int id = user.getUserId();
		User u = repo.findById(id).orElseThrow();
		u.setUserName(u.getUserName());
		u.setUserEmail(u.getUserEmail());
        u.setUserPassword(u.getUserPassword());
        u.setUserType(u.getUserType());
        u.setUserPhone(u.getUserPhone());
		repo.save(u);
		return u;
    }

    @Override
    public void deleteUser(BigInteger userId) {
        int id = userId.intValue(); //convert BigInteger to integer
        repo.deleteById(id);
    }

    @Override
    public void validateUser(User user) {
        //Validates the attributes of a user.
    }
}
