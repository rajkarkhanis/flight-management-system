package com.flights.controller;

import com.flights.bean.Booking;
import com.flights.bean.Flight;
import com.flights.bean.User;
import com.flights.dao.ScheduledFlightDao;
import com.flights.dao.UserDao;
import com.flights.dto.BookingDto;
import com.flights.exception.RecordNotFound;
import com.flights.service.BookingService;
import com.flights.service.UserService;
import lombok.RequiredArgsConstructor;
import com.flights.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class UserController {

    private final BookingService bookingService;

    @Autowired
    private ScheduledFlightDao scheduledFlightDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PassengerService passengerService;

     private final UserService userservice;

    @PostMapping("adduser")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Throwable{
        User newUser = userservice.addUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }
    @PostMapping("/bookFlight")
    public ResponseEntity<Booking> addNewBooking(@RequestBody BookingDto newBooking) throws Exception {
        // Get userId from JWT auth
        int userId = 35;

        if(scheduledFlightDao.findByScheduledFlightId(newBooking.getScheduledFlightId()) == null)
            throw new RecordNotFound("Entered ScheduledFlight object does not exist");

        Booking newBookingObj = new Booking(
                userDao.findByUserId(userId),
                LocalDate.now(),
                newBooking.getPassengerList().stream().map(p -> passengerService.createPassenger(p)).collect(Collectors.toList()),
                newBooking.getTicketCost(),
                scheduledFlightDao.findById(newBooking.getScheduledFlightId()).orElseThrow(),
                newBooking.getPassengerList().size()
        );
        bookingService.addBooking(newBookingObj);
        return new ResponseEntity<>(newBookingObj, HttpStatus.OK);
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
    public ResponseEntity<String> deleteBooking(@PathVariable("id") int bookingId) throws RecordNotFound {
        bookingService.deleteBooking(bookingId);
        String message = "Booking deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
