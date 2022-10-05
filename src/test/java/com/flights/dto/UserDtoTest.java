package com.flights.dto;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDtoTest {
    private Validator validator;

    @BeforeEach
     void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
     void addUserTest() {

        UserDto userDto = new UserDto("Customer","Jack","123","9876543210","jk@mail.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertTrue(violations.isEmpty());
    }
    @Test
     void InvalidName() {

        UserDto userDto = new UserDto("Customer","","123","9876543210","jk@mail.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
//        Cannot be empty and minimum 3 characters
       assertEquals(2,violations.size());
    }
    @Test
     void InvalidPhone() {

        UserDto userDto = new UserDto("Customer","Jack","123","98765432","jk@mail.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertEquals(1,violations.size());
    }
    @Test
     void InvalidEmail() {

        UserDto userDto = new UserDto("Customer","Jack","123","9876543210","jkmail.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertEquals(1,violations.size());
    }

}
