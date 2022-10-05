package com.flights.service;

import com.flights.bean.Airport;
import com.flights.bean.Schedule;
import com.flights.dao.AirportDao;
import com.flights.dao.ScheduleDao;
import com.flights.dto.ScheduleDto;
import com.flights.exception.RecordNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ScheduleServiceImplementationTest {

    @Autowired
    ScheduleService scheduleService;

    @MockBean
    ScheduleDao scheduleDao;

    List<Schedule> scheduleList;

    Airport source;
    Airport destination;
    Schedule schedule;

    ScheduleDto scheduleDto;

    @MockBean
    AirportDao airportDao;

    @BeforeEach
    void setup() {
        source = new Airport();
        source.setAirportCode("BOM");
        source.setAirportLocation("Mumbai");
        source.setAirportName("Mumbai International Airport");

        destination = new Airport();
        destination.setAirportCode("GOI");
        destination.setAirportLocation("Dabolim");
        destination.setAirportName("Goa International Airport");

        LocalDateTime departureTime = LocalDateTime.of(2022, Month.OCTOBER, 16, 15, 35, 0);
        LocalDateTime arrivalTime = LocalDateTime.of(2022, Month.OCTOBER, 16, 17, 5, 0);

        schedule = new Schedule();
        schedule.setSourceAirport(source);
        schedule.setDestinationAirport(destination);
        schedule.setArrivalTime(arrivalTime);
        schedule.setDepartureTime(departureTime);

        scheduleDto = new ScheduleDto();
        scheduleDto.setArrivalTime(arrivalTime);
        scheduleDto.setDepartureTime(departureTime);
        scheduleDto.setDestinationAirportCode("GOI");
        scheduleDto.setSourceAirportCode("BOM");

        scheduleList = new ArrayList<>();

    }

    @Test
    void addSchedule() {
        Mockito.when(airportDao.findByAirportCode(scheduleDto.getDestinationAirportCode()))
                .thenReturn(schedule.getDestinationAirport());

        Mockito.when(airportDao.findByAirportCode(scheduleDto.getSourceAirportCode()))
                .thenReturn(schedule.getSourceAirport());

        Mockito.when(scheduleDao.save(schedule)).thenReturn(schedule);

        Schedule newSchedule = scheduleService.addSchedule(scheduleDto);
        assertThat(newSchedule.getSourceAirport()).isEqualTo(schedule.getSourceAirport());
        assertThat(newSchedule.getDestinationAirport()).isEqualTo(schedule.getDestinationAirport());
        assertThat(newSchedule.getArrivalTime()).isEqualTo(schedule.getArrivalTime());
        assertThat(newSchedule.getDepartureTime()).isEqualTo(schedule.getDepartureTime());
    }

    @Test
    void viewSchedule() {
        Mockito.when(scheduleDao.findAll())
                .thenReturn(scheduleList);
        assertThat(scheduleService.viewSchedule()).isEqualTo(scheduleList);
    }

    @Test
    void viewAllSchedules() {
        Mockito.when(airportDao.save(source)).thenReturn(source);
        Mockito.when(airportDao.save(destination)).thenReturn(destination);
        Mockito.when(scheduleDao.save(schedule)).thenReturn(schedule);
        Mockito.when(scheduleDao.findAll()).thenReturn(scheduleList);
        assertThat(scheduleService.viewSchedule()).isEqualTo(scheduleList);
    }

    @Test
    void testModifySchedule() throws RecordNotFound {
        Mockito.when(scheduleDao.findById(schedule.getScheduleId()))
                .thenReturn(Optional.ofNullable(schedule));

        Mockito.when(scheduleDao.save(schedule)).thenReturn(schedule);

        System.out.println(scheduleService.modifySchedule(scheduleDto));
        assertThat(scheduleService.modifySchedule(scheduleDto)).isEqualTo(schedule);
    }

    @Test
    void testDeleteSchedule() {
        Mockito.when(scheduleDao.findById(schedule.getScheduleId()))
                .thenReturn(Optional.ofNullable(schedule));

        Mockito.when(scheduleDao.existsById(schedule.getScheduleId()))
                .thenReturn(false);

        assertFalse(scheduleDao.existsById(schedule.getScheduleId()));
    }
}
