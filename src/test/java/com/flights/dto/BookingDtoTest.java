package com.flights.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BookingDtoTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAddBooking() {
        List<PassengerDto> list = new ArrayList<>();
        BookingDto bookingDto = new BookingDto(list, 500.00, 1);
        Set<ConstraintViolation<BookingDto>> violations = validator.validate(bookingDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidPassengerList() {
        List<PassengerDto> list = null;
        BookingDto bookingDto = new BookingDto(list, 500.00, 1);
        Set<ConstraintViolation<BookingDto>> violations = validator.validate(bookingDto);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidTicketCostNull() {
        List<PassengerDto> list = new ArrayList<>();
        BookingDto bookingDto = new BookingDto(list, null, 1);
        Set<ConstraintViolation<BookingDto>> violations = validator.validate(bookingDto);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidTicketCostNegative() {
        List<PassengerDto> list = new ArrayList<>();
        BookingDto bookingDto = new BookingDto(list, -500.00, 1);
        Set<ConstraintViolation<BookingDto>> violations = validator.validate(bookingDto);
        assertEquals(1, violations.size());
    }
}
