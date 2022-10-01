package com.flights.service;

import com.flights.bean.Booking;
import com.flights.bean.Passenger;
import com.flights.bean.ScheduledFlight;
import com.flights.dao.BookingDao;
import com.flights.exception.InvalidPassengerUIN;
import com.flights.exception.RecordNotFound;
import com.flights.exception.SeatNotAvailable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements  BookingService{

    @Autowired
    BookingDao repo;

    @Autowired
    ScheduledFlight scheduledFlightRepo;

    @Override
    public Booking addBooking(Booking booking) throws Exception {
        validateBooking(booking);
        for (Passenger p: booking.getPassengerList())
            validatePassenger(p);
        repo.save(booking);
        return booking;
    }

    @Override
    public Booking modifyBooking(Booking booking) throws RecordNotFound {
        int id = booking.getBookingId();
        if(repo.findById(id) == null)
            throw new RecordNotFound("Booking object does not exist");
        Booking b = repo.findById(id).orElseThrow();
        b.setBookingDate(booking.getBookingDate());
        b.setPassengerList(booking.getPassengerList());
        b.setTicketCost(booking.getTicketCost());
        b.setFlight(booking.getFlight());
        return b;
    }

    @Override
    public Booking viewBooking(int bookingId) throws RecordNotFound {
        if(repo.findById(bookingId) == null)
            throw new RecordNotFound("Booking object does not exist");
        Booking b = repo.findById(bookingId).orElseThrow();
        return b;
    }

    @Override
    public List<Booking> viewBooking() {
        List<Booking> list = repo.findAll();
        return list;
    }

    @Override
    public void deleteBooking(int bookingId) throws RecordNotFound {
        if(repo.findById(bookingId) == null)
            throw new RecordNotFound("Booking object does not exist");
        Booking b = repo.findById(bookingId).orElseThrow();
        repo.delete(b);
    }

    @Override
    public void validateBooking(Booking booking) throws Exception {
        // booking.getPassengerList().size() should be less than available seats in scheduled flight
        if(booking.getPassengerList().size() > scheduledFlightRepo.getAvailableSeats()) {
            throw new SeatNotAvailable("Passenger list exceeds available seats");
        }
    }

    @Override
    public void validatePassenger(Passenger passenger) throws InvalidPassengerUIN {
        // Validate if passengerUIN is of 12 digits
        if (passenger.getPassengerUIN().toString().length() != 12) {
            throw new InvalidPassengerUIN("UIN is not 12 digits");
        }
    }
}
