package com.flights.service;

import com.flights.bean.User;
import com.flights.dao.UserDao;
import com.flights.dto.UserDto;
import com.flights.exception.InvalidEmail;
import com.flights.exception.InvalidPhoneNumber;
import com.flights.exception.RecordNotFound;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplementationTest {
    @Autowired
    UserService userservice;
    @MockBean
    UserDao dao;

    @Test
    void testaddUser() throws InvalidEmail, InvalidPhoneNumber {
        User u = new User();
        u.setUserId(1);
        u.setUserType("Customer");
        u.setUserName("Ria");
        u.setUserPassword("123");
        u.setUserEmail("Ria@gmail.com");
        u.setUserPhone("9876543012");

        UserDto userDto = new UserDto(
                u.getUserType(),
                u.getUserName(),
                u.getUserPassword(),
                u.getUserPhone(),
                u.getUserEmail()
        );

        Mockito.when(dao.save(u)).thenReturn(u);
        User newUser = userservice.addUser(userDto);
        assertThat(newUser.getUserName()).isEqualTo(u.getUserName());
        assertThat(newUser.getUserPhone()).isEqualTo(u.getUserPhone());
        assertThat(newUser.getUserType()).isEqualTo(u.getUserType());
        assertThat(newUser.getUserEmail()).isEqualTo(u.getUserEmail());
    }

    @Test
    void passwordEncoderTest() throws InvalidEmail, InvalidPhoneNumber{

        User u = new User();
        u.setUserId(1);
        u.setUserType("Customer");
        u.setUserName("Ria");
        u.setUserPassword("123");
        u.setUserEmail("Ria@gmail.com");
        u.setUserPhone("9876543012");

        UserDto userDto = new UserDto(
                u.getUserType(),
                u.getUserName(),
                u.getUserPassword(),
                u.getUserPhone(),
                u.getUserEmail()
        );

        Mockito.when(dao.save(u)).thenReturn(u);
        User newUser = userservice.addUser(userDto);
assertThat(BCrypt.checkpw("123",newUser.getUserPassword()));

    }
    @Test
    void viewUser() throws RecordNotFound {
        User u1 = new User();
        u1.setUserId(1);
        u1.setUserType("Customer");
        u1.setUserName("Jia");
        u1.setUserPassword("123");
        u1.setUserEmail("Jia@gmail.com");
        u1.setUserPhone("9876543012");

        Optional<User> u2 = Optional.of(u1);
        Mockito.when(dao.findById(1)).thenReturn(u2);
        assertThat(userservice.viewUser(BigInteger.valueOf(1))).isEqualTo(u1);
    }

    @Test
    void testViewUser() {
        User u = new User();
        u.setUserId(1);
        u.setUserType("Customer");
        u.setUserName("Ria");
        u.setUserPassword("123");
        u.setUserEmail("Ria@gmail.com");
        u.setUserPhone("9876543012");

        User u1 = new User();
        u1.setUserId(2);
        u1.setUserType("Customer");
        u1.setUserName("Tia");
        u1.setUserPassword("123");
        u1.setUserEmail("Tia@gmail.com");
        u1.setUserPhone("8907654321");

        List<User> userList = new ArrayList<>();
        userList.add(u);
        userList.add(u1);

        Mockito.when(dao.findAll()).thenReturn(userList);
        assertThat(userservice.viewUser()).isEqualTo(userList);
    }

    @Test
    void updateUser() throws InvalidEmail, RecordNotFound, InvalidPhoneNumber {
        User u1 = new User();
        u1.setUserId(1);
        u1.setUserType("Customer");
        u1.setUserName("Ria");
        u1.setUserPassword("123");
        u1.setUserEmail("Ria@gmail.com");
        u1.setUserPhone("9876543012");
        Optional<User> u2 = Optional.of(u1);

        Mockito.when(dao.findById(1)).thenReturn(u2);

        Mockito.when(dao.save(u1)).thenReturn(u1);
        u1.setUserType("Customer");
        u1.setUserName("Tia");
        u1.setUserPassword("123");
        u1.setUserEmail("Pia@gmail.com");
        u1.setUserPhone("8907654321");

        UserDto u3 = new UserDto();
        u3.setUserId(1);
        u3.setUserType("Customer");
        u3.setUserName("Tia");
        u3.setUserPassword("123");
        u3.setUserEmail("Pia@gmail.com");
        u3.setUserPhone("8907654321");
        assertThat(userservice.updateUser(u3)).isEqualTo(u1);
    }

    @Test
    void deleteUser() {
        User u1 = new User();
        u1.setUserId(1);
        u1.setUserType("Customer");
        u1.setUserName("Tia");
        u1.setUserPassword("123");
        u1.setUserEmail("Pia@gmail.com");
        u1.setUserPhone("8907654321");
        Optional<User> u2 = Optional.of(u1);

        Mockito.when(dao.findById(1)).thenReturn(u2);
        Mockito.when(dao.existsById(u1.getUserId())).thenReturn(false);
        assertFalse(dao.existsById(u1.getUserId()));
    }

    //@Test
    //void validateUser() {
    //}
}