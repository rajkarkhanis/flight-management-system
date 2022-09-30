package com.flights.service;

import com.flights.bean.Booking;
import com.flights.bean.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookingService {

    // Creates a new booking
    public Booking addBooking(Booking booking) throws Exception;

    // Modifies a previous booking.
    public Booking modifyBooking(Booking booking);

    // Retrieves a booking made
    //by the user based on the booking id.
    public List<Booking> viewBooking(int bookingId);

    // Retrieves a list of all the bookings made.
    public List<Booking> viewBooking();

    // Deletes a previous booking identifiable by the ‘bookingId’
    public void deleteBooking(int bookingId);

    // Validates the attributes of a booking
    public void validateBooking(Booking booking) throws Exception;

    // Validates the attributes of a passenger.
    public void validatePassenger(Passenger passenger);

}
