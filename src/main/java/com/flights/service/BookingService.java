package com.flights.service;

import com.flights.bean.Booking;
import com.flights.bean.Passenger;
import com.flights.dto.BookingDto;
import com.flights.exception.InvalidDateTime;
import com.flights.exception.InvalidPassengerUIN;
import com.flights.exception.RecordNotFound;
import com.flights.exception.SeatNotAvailable;

import java.time.LocalDate;
import java.util.List;


public interface BookingService {

    // Creates a new booking
    Booking addBooking(BookingDto booking, String bearerToken) throws RecordNotFound, InvalidDateTime, SeatNotAvailable, InvalidPassengerUIN ;

    // Modifies a previous booking.
    Booking modifyBooking(Booking booking) throws RecordNotFound, InvalidDateTime, SeatNotAvailable, InvalidPassengerUIN ;

    // Retrieves a booking made
    //by the user based on the booking id.
    // Differs from PDF
    Booking viewBooking(int bookingId) throws RecordNotFound;

    // Retrieves a list of all the bookings made.
    List<Booking> viewBooking();

    List<Booking> viewBookingsForUser(String username) throws RecordNotFound;

    // Deletes a previous booking identifiable by the ‘bookingId’
    void deleteBooking(int bookingId) throws RecordNotFound;

    // Validates the attributes of a booking
    static void validateBooking(Booking booking) throws InvalidDateTime,SeatNotAvailable {

        // Validate if bookingDate is not elapsed
        if(booking.getBookingDate().isBefore(LocalDate.now())) {
            throw new InvalidDateTime("Entered booking date is a past date");
        }

        // booking.noOfPassengers should be less than available seats in scheduled flight
        if(booking.getNoOfPassengers() > booking.getScheduledFlight().getAvailableSeats()) {
            throw new SeatNotAvailable("Passenger list exceeds available seats");
        }

    }

    // Validates the attributes of a passenger.
    static void validatePassenger(Passenger passenger) throws InvalidPassengerUIN {

        // Validate if passengerUIN is of 12 digits
        if (passenger.getPassengerUIN().length() != 12) {
            throw new InvalidPassengerUIN("UIN is not 12 digits");
        }

    }

    // Overloaded addBooking for testing without user auth
    Booking addBooking(Booking booking) throws RecordNotFound, InvalidDateTime, SeatNotAvailable,InvalidPassengerUIN;
}
