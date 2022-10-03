package com.flights.service;

import com.flights.bean.User;
import com.flights.dao.UserDao;
import com.flights.exception.InvalidEmail;
import com.flights.exception.InvalidPhoneNumber;
import com.flights.exception.RecordNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import java.util.regex.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    UserDao repo;

    @Override
    public User addUser(User user) throws InvalidEmail, InvalidPhoneNumber {
        this.validateUser(user);
        repo.save(user);
        return user;
    }

    @Override
    public User viewUser(BigInteger userId) {
        Supplier s= ()->new RecordNotFound("User doesn't exist in the database");
        int id = userId.intValue(); //convert BigInteger to integer
        User u = repo.findById(id).orElseThrow(s);
		return u;
    }

    @Override
    public List<User> viewUser() {
        List<User> user = repo.findAll();
		return user;
    }

    @Override
    public User updateUser(User user) throws InvalidEmail, InvalidPhoneNumber {
        int id = user.getUserId();
        Supplier s= ()->new RecordNotFound("User doesn't exist in the database");
        User u = repo.findById(id).orElseThrow(s);
        this.validateUser(user);
        u.setUserName(user.getUserName());
        u.setUserEmail(user.getUserEmail());
        u.setUserPassword(user.getUserPassword());
        u.setUserType(user.getUserType());
        u.setUserPhone(user.getUserPhone());
        repo.save(u);
        return u;
    }

    @Override
    public void deleteUser(BigInteger userId) throws RecordNotFound {
        int id = userId.intValue(); //convert BigInteger to integer
        Supplier s= ()->new RecordNotFound("User doesn't exist in the database");
        User u = repo.findById(id).orElseThrow(s);
        repo.deleteById(id);
    }

    @Override
    public void validateUser(User user) throws InvalidEmail,InvalidPhoneNumber {
        String regex_email = "[a-zA-Z_][a-zA-Z0-9_]*@[a-zA-Z]+[.][a-zA-Z]+";
        Pattern p = Pattern.compile(regex_email);
        Matcher m = p.matcher(user.getUserEmail());
        if(m.matches()!=true)
            throw new InvalidEmail("Invalid Email-Id");

        String regex_phone = "[1-9][0-9]{9}";
        Pattern p1 = Pattern.compile(regex_phone);
        Matcher m1 = p1.matcher(user.getUserPhone());
        if(m1.matches()!=true)
            throw new InvalidPhoneNumber("Phone number should contain only 10 Digits & should not start with 0");

    }
}
