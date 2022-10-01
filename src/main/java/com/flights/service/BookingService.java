package com.flights.service;

import com.flights.bean.Booking;
import com.flights.bean.Passenger;
import com.flights.exception.InvalidPassengerUIN;
import com.flights.exception.RecordNotFound;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookingService {

    // Creates a new booking
    public Booking addBooking(Booking booking) throws Exception;

    // Modifies a previous booking.
    public Booking modifyBooking(Booking booking) throws RecordNotFound;

    // Retrieves a booking made
    //by the user based on the booking id.
    // Differs from PDF
    public Booking viewBooking(int bookingId) throws RecordNotFound;

    // Retrieves a list of all the bookings made.
    public List<Booking> viewBooking();

    // Deletes a previous booking identifiable by the ‘bookingId’
    public void deleteBooking(int bookingId) throws RecordNotFound;

    // Validates the attributes of a booking
    public void validateBooking(Booking booking) throws Exception;

    // Validates the attributes of a passenger.
    public void validatePassenger(Passenger passenger) throws InvalidPassengerUIN;

}
