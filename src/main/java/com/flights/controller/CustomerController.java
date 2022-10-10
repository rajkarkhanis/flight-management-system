package com.flights.controller;

import com.flights.bean.*;
import com.flights.dto.BookingDto;
import com.flights.dto.UserDto;
import com.flights.exception.*;
import com.flights.service.BookingService;
import com.flights.service.ScheduledFlightService;
import com.flights.service.UserService;
import com.flights.utils.AirportDateWrapper;
import com.flights.utils.CustomTokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final BookingService bookingService;
    private final ScheduledFlightService scheduledFlightService;
    private final UserService userService;


//     CREATE A NEW USER
    @PostMapping("adduser")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserDto user) throws InvalidEmail, InvalidPhoneNumber {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }
    @PutMapping("modifyuser")
    public ResponseEntity<User> modifyUser(@RequestBody @Valid UserDto user) throws InvalidEmail, InvalidPhoneNumber,RecordNotFound {
        User newUser = userService.updateUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

//    FLIGHT BOOKING
    @PostMapping("/bookFlight")
    public ResponseEntity<Booking> addNewBooking(@RequestBody BookingDto newBooking,@RequestHeader("Authorization") String bearerToken) throws RecordNotFound, InvalidDateTime, SeatNotAvailable, InvalidPassengerUIN {
       Booking newBookingObj = bookingService.addBooking(newBooking,bearerToken);
        return new ResponseEntity<>(newBookingObj, HttpStatus.OK);
    }

    @GetMapping("/viewBooking")
    public ResponseEntity<List<Booking>> viewBookingByUsername(@RequestHeader("Authorization") String bearerToken) throws RecordNotFound{
        String username= CustomTokenParser.parseJwt(bearerToken);
        List<Booking> bookingList = bookingService.viewBookingsForUser(username);
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

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody @Valid UserDto user) throws RecordNotFound, InvalidEmail, InvalidPhoneNumber{
    return userService.updateUser(user);
}
    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int userId) throws RecordNotFound{
        BigInteger bi = BigInteger.valueOf(userId);
        userService.deleteUser(bi);
    }

}
