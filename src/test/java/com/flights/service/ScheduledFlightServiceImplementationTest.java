package com.flights.service;

import com.flights.bean.Airport;
import com.flights.bean.Flight;
import com.flights.bean.Schedule;
import com.flights.bean.ScheduledFlight;
import com.flights.dao.FlightDao;
import com.flights.dao.ScheduledFlightDao;
import com.flights.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ScheduledFlightServiceImplementationTest {

    @Autowired
    ScheduledFlightService service;

    @MockBean
    ScheduledFlightDao scheduledFlightDao;

    @MockBean
    FlightDao flightDao;

    Flight flight;
    Schedule schedule;
    ScheduledFlight scheduledFlight;
    Airport source;
    Airport destination;

    @BeforeEach
    void setup() {
        flight = new Flight();
        flight.setFlightNumber(BigInteger.valueOf(1));
        flight.setFlightModel("Boeing 787");
        flight.setCarrierName("Air India");
        flight.setSeatCapacity(380);

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
        schedule.setScheduleId(2);
        schedule.setSourceAirport(source);
        schedule.setDestinationAirport(destination);
        schedule.setArrivalTime(arrivalTime);
        schedule.setDepartureTime(departureTime);

        scheduledFlight = new ScheduledFlight();
        scheduledFlight.setScheduledFlightId(3);
        scheduledFlight.setFlight(flight);
        scheduledFlight.setSchedule(schedule);
        scheduledFlight.setAvailableSeats(300);
    }

    @Test
    void scheduleFlight() throws  SeatNotAvailable, RecordAlreadyExists, InvalidDateTime , RecordNotFound{
        Mockito.when(scheduledFlightDao.save(scheduledFlight)).thenReturn(scheduledFlight);
        assertThat(service.scheduleFlight(scheduledFlight)).isEqualTo(scheduledFlight);
    }

    @Test
    void viewScheduledFlightsBetweenTwoAirportsOnDate() {
        LocalDate date = LocalDate.of(2022, Month.OCTOBER, 16);
        Mockito.when(scheduledFlightDao.getScheduledFlights(source, destination, date))
                .thenReturn(List.of(scheduledFlight));
        assertThat(service.viewScheduledFlights(source, destination, date))
                .isEqualTo(List.of(scheduledFlight));
    }

    @Test
    void viewScheduledFlightByFlightNumber() throws RecordNotFound {
        /*
        Find List of SF by flightNumber
        1. Get Flight by flightNumber
        2. Get SF by Flight
        3. Return as list
         */
        Mockito.when(flightDao.findById(BigInteger.valueOf(1)))
                .thenReturn(Optional.ofNullable(flight));

        Mockito.when(scheduledFlightDao.getScheduledFlightByFlight(flight))
                .thenReturn(scheduledFlight);

        assertThat(service.viewScheduledFlights(BigInteger.valueOf(1)))
                .isEqualTo(List.of(scheduledFlight));
    }

    @Test
    void viewAllScheduledFlights() {
        Mockito.when(scheduledFlightDao.findAll())
                .thenReturn(List.of(scheduledFlight));

        assertThat(service.viewScheduledFlight()).isEqualTo(List.of(scheduledFlight));
    }

    @Test
    void modifyScheduledFlight() throws RecordNotFound {
        Schedule newSchedule = scheduledFlight.getSchedule();
        LocalDateTime newTime = newSchedule.getArrivalTime().minusHours(1);
        newSchedule.setArrivalTime(newTime);
        scheduledFlight.setSchedule(newSchedule);

        Mockito.when(scheduledFlightDao.save(scheduledFlight)).thenReturn(scheduledFlight);
        Mockito.when(scheduledFlightDao.findById(3)).thenReturn(Optional.ofNullable(scheduledFlight));
        assertThat(service.modifyScheduledFlight(flight, newSchedule, 3)).isEqualTo(scheduledFlight);
    }

    @Test
    void deleteScheduledFlight() throws RecordNotFound {
        Mockito.when(scheduledFlightDao.existsById(3)).thenReturn(false);
        assertFalse(scheduledFlightDao.existsById(3));
    }
}