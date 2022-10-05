package com.flights.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigInteger;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FlightDtoTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAddFlight() {
        FlightDto flightDto = new FlightDto(BigInteger.ONE, "A320", "Airbus", 100);
        Set<ConstraintViolation<FlightDto>> violations = validator.validate(flightDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    @Disabled
    void testInvalidFlightId() {
        FlightDto flightDto = new FlightDto(null, "A320", "Airbus", 100);
        Set<ConstraintViolation<FlightDto>> violations = validator.validate(flightDto);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidFlightModel() {
        FlightDto flightDto = new FlightDto(BigInteger.ONE, "", "Airbus", 100);
        Set<ConstraintViolation<FlightDto>> violations = validator.validate(flightDto);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidCarrierName() {
        FlightDto flightDto = new FlightDto(BigInteger.ONE, "A320", "", 100);
        Set<ConstraintViolation<FlightDto>> violations = validator.validate(flightDto);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidSeatCapacityNull() {
        FlightDto flightDto = new FlightDto(BigInteger.ONE, "A320", "Airbus", null);
        Set<ConstraintViolation<FlightDto>> violations = validator.validate(flightDto);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidSeatCapacityZero() {
        FlightDto flightDto = new FlightDto(BigInteger.ONE, "A320", "Airbus", 0);
        Set<ConstraintViolation<FlightDto>> violations = validator.validate(flightDto);
        assertEquals(1, violations.size());
    }
}
