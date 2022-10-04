package com.flights.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.flights.bean.Booking;
import com.flights.bean.Passenger;
import com.flights.dao.ScheduledFlightDao;
import com.flights.dao.UserDao;
import com.flights.dto.BookingDto;
import com.flights.exception.RecordNotFound;
import com.flights.service.BookingService;
import com.flights.service.PassengerService;
import com.flights.service.PassengerServiceImpl;
import com.flights.service.ScheduledFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookingAPI")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ScheduledFlightDao scheduledFlightDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/addBooking")
    public Booking addNewBooking(@RequestBody BookingDto newBooking,@RequestHeader("Authorization") String bearerToken) throws Exception {
       return  bookingService.addBooking(newBooking,bearerToken);
    }

    @PutMapping("/updateBooking")
    public Booking updateBooking(@RequestBody Booking modifiedBooking) throws Exception {
        return bookingService.modifyBooking(modifiedBooking);
    }

    @GetMapping("/getBookingById/{id}")
    public Booking getBookingById(@PathVariable("id") int bookingId) throws RecordNotFound {
        return bookingService.viewBooking(bookingId);
    }

    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings() {
        return bookingService.viewBooking();
    }

    @DeleteMapping("/cancelBookingById/{bookingId}")
    public void deleteBooking(@PathVariable("bookingId") int bookingId) throws RecordNotFound {
        bookingService.deleteBooking(bookingId);
    }

}
