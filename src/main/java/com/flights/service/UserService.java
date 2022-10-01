package com.flights.service;

import com.flights.bean.User;
import com.flights.exception.InvalidEmail;
import com.flights.exception.InvalidPhoneNumber;
import com.flights.exception.RecordNotFound;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    User addUser(User user) throws InvalidEmail, InvalidPhoneNumber;
    //Adds a new user.

    User viewUser(BigInteger userId) throws RecordNotFound;
    //Shows the details of a user identifiable by the user id.

    List<User> viewUser();
    //Shows the details of all users.

    User updateUser(User user) throws RecordNotFound, InvalidEmail, InvalidPhoneNumber;
    //Updates the details of a user.

    void deleteUser(BigInteger userId) throws RecordNotFound;
    //Removes a user as per the user id.

    void validateUser(User user) throws InvalidEmail, InvalidPhoneNumber;
    //Validates the attributes of a user.

}
