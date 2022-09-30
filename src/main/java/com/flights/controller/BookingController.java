package com.flights.controller;

import com.flights.bean.Booking;
import com.flights.bean.User;
import com.flights.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookingAPI")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/addBooking")
    public Booking addNewBooking(@RequestBody Booking newBooking) throws Exception {
        // User u = new User(123, "admin", "admin", "user", "75387539834", "abc@gmail.com");
        Booking b = bookingService.addBooking(newBooking);
        return b;
    }

    @PutMapping("/updateBooking")
    public Booking updateBooking(@RequestBody Booking modifyBooking) {
        Booking b = bookingService.modifyBooking(modifyBooking);
        return b;
    }

    @GetMapping("/getBookingById/{id}")
    public Booking getBookingById(@PathVariable("id") int bookingId) {
        return bookingService.viewBooking(bookingId);
    }

    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings() {
        return bookingService.viewBooking();
    }

}
