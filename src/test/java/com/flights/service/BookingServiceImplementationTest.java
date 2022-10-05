package com.flights.service;

import com.flights.bean.*;
import com.flights.dao.BookingDao;
import com.flights.dao.ScheduledFlightDao;
import com.flights.dto.BookingDto;
import com.flights.exception.RecordNotFound;
import org.assertj.core.api.Assertions;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class BookingServiceImplementationTest {

    @Autowired
    BookingService bookingService;

    @MockBean
    BookingDao bookingDao;

    @MockBean
    ScheduledFlightDao scheduledFlightDao;

    Flight flight;
    Schedule schedule;
    ScheduledFlight scheduledFlight;
    Airport source;
    Airport destination;
    Booking booking;
    User user;

    Passenger passenger1;
    Passenger passenger2;

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

        user = new User();
        user.setUserName("Sadichchha");
        user.setUserPassword("abc123");
        user.setUserType("CUSTOMER");
        user.setUserPhone("9876543210");
        user.setUserEmail("abc@gmail.com");

        passenger1 = new Passenger();
        passenger1.setLuggage(9.2);
        passenger1.setPassengerName("Sam");
        passenger1.setPassengerAge(31);
        passenger1.setPassengerUIN("123456789123");
        passenger1.setPnrNumber(BigInteger.valueOf(1234));

        passenger2 = new Passenger();
        passenger2.setLuggage(7.2);
        passenger2.setPassengerName("Samantha");
        passenger2.setPassengerAge(29);
        passenger2.setPassengerUIN("123456449123");
        passenger2.setPnrNumber(BigInteger.valueOf(4321));

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(passenger1);
        passengerList.add(passenger2);

        booking = new Booking();
        booking.setBookingId(1);
        booking.setUserId(user);
        booking.setScheduledFlight(scheduledFlight);
        booking.setBookingDate(LocalDate.now());
        booking.setPassengerList(passengerList);
        booking.setNoOfPassengers(2);
        booking.setTicketCost(4000.23);
    }

    // TODO: Pass BookingDto and bearerToken
//    @Test
//    void addBooking() throws Exception {
//        Mockito.when(bookingDao.save(booking)).thenReturn(booking);
//        assertThat(bookingService.addBooking(bookingDto)).isEqualTo(booking);
//    }

    @Test
    void modifyBooking() throws Exception {
        ScheduledFlight newScheduledFlight = booking.getScheduledFlight();
        newScheduledFlight.setAvailableSeats(20);
        booking.setScheduledFlight(newScheduledFlight);

        Mockito.when(bookingDao.save(booking)).thenReturn(booking);
        Mockito.when(bookingDao.findById(1)).thenReturn(Optional.ofNullable(booking));
        Assertions.assertThat(bookingService.modifyBooking(booking)).isEqualTo(booking);

    }

    @Test
    void viewBooking() {
        Mockito.when(bookingDao.findAll())
                .thenReturn(List.of(booking));

        Assertions.assertThat(bookingService.viewBooking()).isEqualTo(List.of(booking));

    }

    @Test
    void testviewBooking() throws RecordNotFound {
        Optional<Booking> b = Optional.of(booking);
        Mockito.when(bookingDao.findById(1)).thenReturn(b);
        assertThat(bookingService.viewBooking(1)).isEqualTo(booking);
    }
    @Test
    void deleteBooking() {
        Mockito.when(bookingDao.existsById(1)).thenReturn(false);
        assertFalse(bookingDao.existsById(1));

    }

}
