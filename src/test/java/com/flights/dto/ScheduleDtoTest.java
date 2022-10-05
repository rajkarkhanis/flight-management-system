package com.flights.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ScheduleDtoTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void addScheduleTest() {

        ScheduleDto scheduleDto= new ScheduleDto("BOM","DEL", LocalDateTime.of(2022, Month.NOVEMBER,12,12,20),LocalDateTime.of(2022, Month.NOVEMBER,12,16,30));
        Set<ConstraintViolation<ScheduleDto>> violations = validator.validate(scheduleDto);
        assertTrue(violations.isEmpty());
    }
    @Test
    void InvalidSource() {

        ScheduleDto scheduleDto= new ScheduleDto("B","DEL", LocalDateTime.of(2022, Month.NOVEMBER,12,12,20),LocalDateTime.of(2022, Month.NOVEMBER,12,16,30));
        Set<ConstraintViolation<ScheduleDto>> violations = validator.validate(scheduleDto);
        assertEquals(1,violations.size());
    }
    @Test
    void InvalidDestination() {

        ScheduleDto scheduleDto= new ScheduleDto("BOM","DEEEE", LocalDateTime.of(2022, Month.NOVEMBER,12,12,20),LocalDateTime.of(2022, Month.NOVEMBER,12,16,30));
        Set<ConstraintViolation<ScheduleDto>> violations = validator.validate(scheduleDto);
        assertEquals(1,violations.size());
    }
    @Test
    void PastArrival() {

        ScheduleDto scheduleDto= new ScheduleDto("BOM","DEL", LocalDateTime.of(2022, Month.SEPTEMBER,12,12,20),LocalDateTime.of(2022, Month.NOVEMBER,12,16,30));
        Set<ConstraintViolation<ScheduleDto>> violations = validator.validate(scheduleDto);
        assertEquals(1,violations.size());
    }
    @Test
    void PastDeparture() {

        ScheduleDto scheduleDto= new ScheduleDto("BOM","DEL", LocalDateTime.of(2022, Month.OCTOBER,12,12,20),LocalDateTime.of(2022, Month.SEPTEMBER,12,16,30));
        Set<ConstraintViolation<ScheduleDto>> violations = validator.validate(scheduleDto);
        assertEquals(1,violations.size());
    }
}
