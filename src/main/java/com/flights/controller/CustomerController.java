package com.flights.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.flights.bean.*;
import com.flights.dao.ScheduledFlightDao;
import com.flights.dao.UserDao;
import com.flights.dto.BookingDto;
import com.flights.exception.RecordNotFound;
import com.flights.service.BookingService;
import com.flights.service.ScheduledFlightService;
import com.flights.service.UserService;
import com.flights.utils.AirportDateWrapper;
import lombok.RequiredArgsConstructor;
import com.flights.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final BookingService bookingService;
    private final ScheduledFlightService scheduledFlightService;
    private final UserService userservice;


//     CREATE A NEW USER
    @PostMapping("adduser")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Throwable{
        User newUser = userservice.addUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

//    FLIGHT BOOKING
    @PostMapping("/bookFlight")
    public ResponseEntity<Booking> addNewBooking(@RequestBody BookingDto newBooking,@RequestHeader("Authorization") String bearerToken) throws Exception {
       Booking newBookingObj = bookingService.addBooking(newBooking,bearerToken);
        return new ResponseEntity<>(newBookingObj, HttpStatus.OK);
    }

    //TODO: make it specific for user
    @GetMapping("/viewBooking")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookingList = bookingService.viewBooking();
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }


    @GetMapping("/viewBooking/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") int bookingId) throws RecordNotFound {
        Booking b = bookingService.viewBooking(bookingId);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") int bookingId) throws RecordNotFound {
        bookingService.deleteBooking(bookingId);
        String message = "Booking deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

//    FLIGHT SCHEDULES
    @GetMapping(value = "/viewScheduledFlightsOnDate")
    public ResponseEntity<List<ScheduledFlight>> viewScheduledFlights(@RequestBody AirportDateWrapper wrapper) {
        Airport source = wrapper.getSource();
        Airport destination = wrapper.getDestination();
        LocalDate date = wrapper.getDate();
        List<ScheduledFlight> scheduledFlightList = scheduledFlightService.viewScheduledFlights(source, destination, date);
        return new ResponseEntity<>(scheduledFlightList, HttpStatus.OK);
    }

    @GetMapping(value = "/viewScheduledFlights")
    public ResponseEntity<List<ScheduledFlight>> viewScheduledFlights() {
        List<ScheduledFlight> scheduledFlightList = scheduledFlightService.viewScheduledFlight();
        return new ResponseEntity<>(scheduledFlightList, HttpStatus.OK);
    }

    @GetMapping(value = "/viewScheduledFlights/{flightNumber}")
    public ResponseEntity<List<ScheduledFlight>> viewScheduledFlightsById(@PathVariable BigInteger flightNumber) throws RecordNotFound {
        List<ScheduledFlight> scheduledFlightList = scheduledFlightService.viewScheduledFlights(flightNumber);
        return new ResponseEntity<>(scheduledFlightList, HttpStatus.OK);
    }
//TODO: view all flights
    //TODO:

}
