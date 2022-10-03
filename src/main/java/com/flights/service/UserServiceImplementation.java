package com.flights.service;

import com.flights.bean.User;
import com.flights.dao.UserDao;
import com.flights.exception.InvalidEmail;
import com.flights.exception.InvalidPhoneNumber;
import com.flights.exception.RecordNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.regex.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

    @Autowired
    UserDao userRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User addUser(User user) throws InvalidEmail, InvalidPhoneNumber {
        this.validateUser(user);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepo.save(user);
        return user;
    }

    @Override
    public User viewUser(BigInteger userId) {
        Supplier s= ()->new RecordNotFound("User doesn't exist in the database");
        int id = userId.intValue(); //convert BigInteger to integer
        User u = userRepo.findById(id).orElseThrow(s);
		return u;
    }

    @Override
    public List<User> viewUser() {
        List<User> user = userRepo.findAll();
		return user;
    }

    @Override
    public User updateUser(User user) throws InvalidEmail, InvalidPhoneNumber {
        int id = user.getUserId();
        Supplier s= ()->new RecordNotFound("User doesn't exist in the database");
		User u = userRepo.findById(id).orElseThrow(s);
        this.validateUser(user);
		u.setUserName(user.getUserName());
		u.setUserEmail(user.getUserEmail());
        u.setUserPassword(user.getUserPassword());
        u.setUserType(user.getUserType());
        u.setUserPhone(user.getUserPhone());
		userRepo.save(u);
		return u;
    }

    @Override
    public void deleteUser(BigInteger userId) throws RecordNotFound {
        int id = userId.intValue(); //convert BigInteger to integer
        Supplier s= ()->new RecordNotFound("User doesn't exist in the database");
        User u = userRepo.findById(id).orElseThrow(s);
        userRepo.deleteById(id);
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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(user==null){
            log.error("User not found");
            throw new UsernameNotFoundException("user not found in db");

        }
        else{
            log.info("User found:{}",username);

        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserType()));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getUserPassword(),authorities);
    }
}
