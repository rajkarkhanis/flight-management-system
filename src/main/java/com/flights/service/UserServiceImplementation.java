package com.flights.service;

import com.flights.bean.User;
import com.flights.dao.UserDao;
import com.flights.dto.UserDto;
import com.flights.exception.InvalidEmail;
import com.flights.exception.InvalidPhoneNumber;
import com.flights.exception.RecordNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.*;
import java.math.BigInteger;
import java.util.List;

@Service @RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserDao userRepo;

    private final PasswordEncoder passwordEncoder;
    @Override
    public User addUser(UserDto user) throws InvalidPhoneNumber {
        User newUser= new User();
        newUser.setUserEmail(user.getUserEmail());
        newUser.setUserName(user.getUserName());
        newUser.setUserPassword(user.getUserPassword());
        newUser.setUserPhone(user.getUserPhone());
        newUser.setUserType(user.getUserType());
        this.validateUser(newUser.getUserPhone());
        newUser.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        log.info("User is: {}",user);
        userRepo.save(newUser);
        return newUser;
    }

    @Override
    public User viewUser(BigInteger userId) throws RecordNotFound{
        int id = userId.intValue(); //convert BigInteger to integer
        return userRepo.findById(id).orElseThrow( ()->new RecordNotFound(User.class.toString()));
    }

    @Override
    public List<User> viewUser() {
      return userRepo.findAll();

    }

    @Override
    public User updateUser(UserDto user) throws InvalidEmail, InvalidPhoneNumber,RecordNotFound {
        int id = user.getUserId();
        User u = userRepo.findById(id).orElseThrow(()->new RecordNotFound(User.class.toString()));
        this.validateUser(user.getUserPhone());
        u.setUserName(user.getUserName());
        u.setUserEmail(user.getUserEmail());
        u.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        u.setUserType(user.getUserType());
        u.setUserPhone(user.getUserPhone());
        userRepo.save(u);
        return u;
    }

    @Override
    public void deleteUser(BigInteger userId) throws RecordNotFound {
        int id = userId.intValue(); //convert BigInteger to integer

        if(userRepo.findById(id).isEmpty())
            throw new RecordNotFound(User.class.toString());

        userRepo.deleteById(id);
    }

    @Override
    public void validateUser(String phone) throws InvalidPhoneNumber {

        String regexPhone = "[1-9][\\d]{9}";
        Pattern p1 = Pattern.compile(regexPhone);
        Matcher m1 = p1.matcher(phone);
        if(!m1.matches())
            throw new InvalidPhoneNumber("Phone number should contain only 10 Digits & should not start with 0");

    }

    @Override
    public User findByUserName(String username) {
        return userRepo.findByUserName(username);
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
