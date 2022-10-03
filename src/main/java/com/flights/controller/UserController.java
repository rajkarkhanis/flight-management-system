package com.flights.controller;

import com.flights.bean.Booking;
import com.flights.bean.Flight;
import com.flights.bean.User;
import com.flights.exception.RecordNotFound;
import com.flights.service.BookingService;
import com.flights.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class UserController {

    private final BookingService bookingService;

     private final UserService userservice;

    @PostMapping("adduser")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Throwable{
        User newUser = userservice.addUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }
    @PostMapping("/bookFlight")
    public ResponseEntity<Booking> addNewBooking(@RequestBody Booking newBooking) throws Exception {
        Booking b = bookingService.addBooking(newBooking);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

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
    public ResponseEntity<String> deleteBooking(@PathVariable int bookingId) throws RecordNotFound {
        bookingService.deleteBooking(bookingId);
        String message = "Booking deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
