package com.flights.service;

import com.flights.bean.User;
import java.math.BigInteger;
import java.util.List;

public interface UserService {

    User addUser(User user);
    //Adds a new user.

    User viewUser(BigInteger userId);
    //Shows the details of a user identifiable by the user id.

    List<User> viewUser();
    //Shows the details of all users.

    User updateUser(User user);
    //Updates the details of a user.

    void deleteUser(BigInteger userId);
    //Removes a user as per the user id.

    void validateUser(User user);
    //Validates the attributes of a user.

}
