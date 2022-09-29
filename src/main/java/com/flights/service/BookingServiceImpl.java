package com.flights.service;

import com.flights.bean.Booking;
import com.flights.bean.Passenger;
import com.flights.bean.ScheduledFlight;
import com.flights.dao.BookingDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BookingServiceImpl implements  BookingService{

    @Autowired
    BookingDao repo;

    @Override
    public Booking addBooking(Booking booking) {
        validateBooking(booking);
        repo.save(booking);
        return booking;
    }

    @Override
    public Booking modifyBooking(Booking booking) {
        BigInteger id = booking.getBookingId();
        Booking b = repo.findById(id.intValue()).orElseThrow();
        b.setBookingDate(booking.getBookingDate());
        b.setPassengerList(booking.getPassengerList());
        b.setTicketCost(booking.getTicketCost());
        b.setFlight(booking.getFlight());
        return b;
    }

    @Override
    public List<Booking> viewBooking(int bookingId) {
        Booking b = repo.findById(bookingId).orElseThrow();
        List<Booking> output = new ArrayList<Booking>();
        output.add(b);
        return output;
    }

    @Override
    public List<Booking> viewBooking() {
        List<Booking> list = repo.findAll();
        return list;
    }

    @Override
    public void deleteBooking(int bookingId) {
        Booking b = repo.findById(bookingId).orElseThrow();
        repo.delete(b);
    }

    @Override
    public void validateBooking(Booking booking) {

        // booking.getPassengerList().size() should be less than available seats in scheduled flight;

    }

    @Override
    public void validatePassenger(Passenger passenger) {

        // Validate if passengerUIN is of 12 digits

    }
}
