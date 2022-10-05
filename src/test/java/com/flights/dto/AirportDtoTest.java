package com.flights.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AirportDtoTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAddAirport() {
        AirportDto airportDto = new AirportDto("ABC", "Mumbai Airport", "Mumbai");
        Set<ConstraintViolation<AirportDto>> violations = validator.validate(airportDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidAirportCode() {
        AirportDto airportDto = new AirportDto("", "Mumbai Airport", "Mumbai");
        Set<ConstraintViolation<AirportDto>> violations = validator.validate(airportDto);
        assertEquals(2, violations.size());
    }

    @Test
    void testInvalidAirportName() {
        AirportDto airportDto = new AirportDto("ABC", "", "Mumbai");
        Set<ConstraintViolation<AirportDto>> violations = validator.validate(airportDto);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidAirportLocation() {
        AirportDto airportDto = new AirportDto("ABC", "", "Mumbai");
        Set<ConstraintViolation<AirportDto>> violations = validator.validate(airportDto);
        assertEquals(1, violations.size());
    }
}
