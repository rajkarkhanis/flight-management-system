package com.flights.service;

import com.flights.bean.Airport;
import com.flights.bean.Flight;
import com.flights.bean.Schedule;
import com.flights.bean.ScheduledFlight;
import com.flights.dao.FlightDao;
import com.flights.dao.ScheduledFlightDao;
import com.flights.exception.SeatNotAvailable;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class ScheduledFlightServiceImplementation implements ScheduledFlightService{

    @Autowired
    ScheduledFlightDao scheduledFlightDao;
    @Autowired
    FlightDao flightDao;

    @Override
    public ScheduledFlight scheduleFlight(ScheduledFlight scheduledFlight) {
        return scheduledFlightDao.save(scheduledFlight);
    }

    @Override
    public List<ScheduledFlight> viewScheduledFlights(Airport firstAirport, Airport secondAirport, LocalDate localDate) {
        return scheduledFlightDao.getScheduledFlights(firstAirport, secondAirport, localDate);
    }

    // Changing return type from PDF Spec
    // PDF mentions return type as Flight
    @Override
    public List<ScheduledFlight> viewScheduledFlights(BigInteger flightNumber) {
        Flight flight = flightDao.findById(flightNumber).orElseThrow();
        return List.of(scheduledFlightDao.getScheduledFlightByFlight(flight));
    }

    @Override
    public List<ScheduledFlight> viewScheduledFlight() {
        return scheduledFlightDao.findAll();
    }

    @Override
    public ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule, Integer flightId) {
        ScheduledFlight scheduledFlight = scheduledFlightDao.findById(flightId).orElseThrow();
        scheduledFlight.setFlight(flight);
        scheduledFlight.setSchedule(schedule);
        return scheduledFlight;
    }

    @Override
    public void deleteScheduledFlight(BigInteger flightNumber) {
        Flight inputFlight = flightDao.findById(flightNumber).orElseThrow();
        ScheduledFlight scheduledFlight = scheduledFlightDao.getScheduledFlightByFlight(inputFlight);
        scheduledFlightDao.delete(scheduledFlight);
    }

    @Override
    public void validateScheduledFlight(ScheduledFlight scheduledFlight) throws SeatNotAvailable {
        // Throw custom exceptions in case of failure
        /*
        scheduledFlight.availableSeats >= 0
        scheduledFlight.Flight.validateFlight();
        scheduledFlight.Schedule.arrivalTime is not elapsed
        scheduledFlight.Schedule.departureTime is not elapsed
        scheduledFlight.Schedule.sourceAirport.airportCode != null
        scheduledFlight.Schedule.sourceAirport.airportName != null
        scheduledFlight.Schedule.sourceAirport.airportLocation != null
         */
        if (scheduledFlight.getAvailableSeats() <= 0) {
            throw new SeatNotAvailable("Seats not available");
        }
    }
}
