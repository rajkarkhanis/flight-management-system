package com.flights.service;

import com.flights.bean.Booking;
import com.flights.bean.Passenger;
import com.flights.bean.ScheduledFlight;
import com.flights.dao.BookingDao;
import com.flights.dao.ScheduledFlightDao;
import com.flights.dto.BookingDto;
import com.flights.exception.InvalidDateTime;
import com.flights.exception.InvalidPassengerUIN;
import com.flights.exception.RecordNotFound;
import com.flights.exception.SeatNotAvailable;
import com.flights.utils.CustomTokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class BookingServiceImpl implements  BookingService{

    private final BookingDao repo;
    private final ScheduledFlightDao scheduledFlightRepo;
    private final UserService userService;
    private final ScheduledFlightService scheduledFlightService;
    private final PassengerService passengerService;
    private final ScheduledFlightDao scheduledFlightDao;
    @Override
    public Booking addBooking(BookingDto newBooking,String bearerToken) throws Exception {
        if(scheduledFlightDao.findByScheduledFlightId(newBooking.getScheduledFlightId()) == null)
            throw new RecordNotFound("Entered ScheduledFlight object does not exist");
        String username = CustomTokenParser.parseJwt(bearerToken);
        Booking booking = new Booking(
                userService.findByUserName(username),
                LocalDate.now(),
                newBooking.getPassengerList().stream().map(passengerService::createPassenger).collect(Collectors.toList()),
                newBooking.getTicketCost(),
                scheduledFlightDao.findById(newBooking.getScheduledFlightId()).orElseThrow(),
                newBooking.getPassengerList().size()
        );



        validateBooking(booking);
        for (Passenger p: booking.getPassengerList())
            validatePassenger(p);

        // Update available seats
        int initialSeatsAvailable = booking.getScheduledFlight().getAvailableSeats();
        int currentSeatsAvailable = initialSeatsAvailable - booking.getNoOfPassengers();
        booking.getScheduledFlight().setAvailableSeats(currentSeatsAvailable);

        booking.setScheduledFlight(scheduledFlightRepo.findByScheduledFlightId(booking.getScheduledFlight().getScheduledFlightId()));
        repo.save(booking);
        return booking;
    }

    @Override
    public Booking modifyBooking(Booking booking) throws Exception {
        int id = booking.getBookingId();

        if(repo.findById(id) == null)
            throw new RecordNotFound("Booking object does not exist");

        validateBooking(booking);
        for (Passenger p: booking.getPassengerList())
            validatePassenger(p);

        Booking b = repo.findById(id).orElseThrow();

        b.setBookingDate(booking.getBookingDate());
        b.setNoOfPassengers(booking.getNoOfPassengers());
        b.setPassengerList(booking.getPassengerList());
        b.setTicketCost(booking.getTicketCost());
        b.setScheduledFlight(booking.getScheduledFlight());

        repo.save(b);
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

        Booking booking = repo.findById(bookingId).orElseThrow();

        //  Update available seats impl
        int initialSeatsAvailable = booking.getScheduledFlight().getAvailableSeats();
        int currentSeatsAvailable = initialSeatsAvailable - booking.getNoOfPassengers();
        booking.getScheduledFlight().setAvailableSeats(currentSeatsAvailable);

        repo.delete(booking);
    }

    @Override
    public void validateBooking(Booking booking) throws Exception {



        // Validate if bookingDate is not elapsed
        if(booking.getBookingDate().isBefore(LocalDate.now())) {
            throw new InvalidDateTime("Entered booking date is a past date");
        }

        // booking.noOfPassengers should be less than available seats in scheduled flight
        if(booking.getNoOfPassengers() > booking.getScheduledFlight().getAvailableSeats()) {
            throw new SeatNotAvailable("Passenger list exceeds available seats");
        }

    }

    @Override
    public void validatePassenger(Passenger passenger) throws InvalidPassengerUIN {

        // Validate if passengerUIN is of 12 digits
        if (passenger.getPassengerUIN().length() != 12) {
            throw new InvalidPassengerUIN("UIN is not 12 digits");
        }

    }
}
