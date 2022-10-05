package com.flights.service;

import com.flights.bean.Airport;
import com.flights.bean.Flight;
import com.flights.bean.Schedule;
import com.flights.bean.ScheduledFlight;
import com.flights.dao.AirportDao;
import com.flights.dao.FlightDao;
import com.flights.dao.ScheduledFlightDao;
import com.flights.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class ScheduledFlightServiceImplementation implements ScheduledFlightService{

    private final ScheduledFlightDao scheduledFlightDao;
    private final FlightDao flightDao;
    private final AirportDao airportDao;

    @Override
    public ScheduledFlight scheduleFlight(ScheduledFlight scheduledFlight) throws SeatNotAvailable, RecordAlreadyExists, InvalidDateTime, RecordNotFound {
        validateScheduledFlight(scheduledFlight);
        return scheduledFlightDao.save(scheduledFlight);
    }

    @Override
    public List<ScheduledFlight> viewScheduledFlights(Airport firstAirport, Airport secondAirport, LocalDate localDate) {
        return scheduledFlightDao.getScheduledFlights(firstAirport, secondAirport, localDate);
    }

    // Changing return type from PDF Spec
    // PDF mentions return type as Flight
    @Override
    public List<ScheduledFlight> viewScheduledFlights(BigInteger flightNumber) throws RecordNotFound {
        Flight flight = flightDao.findById(flightNumber).orElseThrow(
                () -> new RecordNotFound("Flight with number: " + flightNumber + " not found")
        );
        return List.of(scheduledFlightDao.getScheduledFlightByFlight(flight));
    }

    @Override
    public List<ScheduledFlight> viewScheduledFlight() {
        return scheduledFlightDao.findAll();
    }

    @Override
    public ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule, Integer flightId) throws RecordNotFound {
        ScheduledFlight scheduledFlight = scheduledFlightDao.findById(flightId).orElseThrow(
                () -> new RecordNotFound("ScheduledFlight of ID: " + flightId + " doesn't exist")
        );
        scheduledFlight.setFlight(flight);
        scheduledFlight.setSchedule(schedule);
        scheduledFlightDao.save(scheduledFlight);
        return scheduledFlight;
    }

    @Override
    public void deleteScheduledFlight(BigInteger flightNumber) throws RecordNotFound {
        Flight inputFlight = flightDao.findById(flightNumber).orElseThrow(
                () -> new RecordNotFound("Flight with number: " + flightNumber + " doesn't exist")
        );
        ScheduledFlight scheduledFlight = scheduledFlightDao.getScheduledFlightByFlight(inputFlight);
        scheduledFlightDao.delete(scheduledFlight);
    }
    @Override
    public void validateScheduledFlight(ScheduledFlight scheduledFlight) throws SeatNotAvailable, RecordAlreadyExists, InvalidDateTime, RecordNotFound {
        if (scheduledFlightDao.existsById(scheduledFlight.getScheduledFlightId())) {
            throw new RecordAlreadyExists("ScheduledFlight already exists");
        }



        if (scheduledFlight.getAvailableSeats() <= 0) {
            throw new SeatNotAvailable("Seats not available");
        }

        Schedule schedule = scheduledFlight.getSchedule();
        if (schedule.getArrivalTime().isBefore(LocalDateTime.now())) {
            throw new InvalidDateTime("Arrival date & time has elapsed");
        }

        if (schedule.getDepartureTime().isBefore(LocalDateTime.now())) {
            throw new InvalidDateTime("Departure date & time has elapsed");
        }

        if (!airportDao.existsByAirportCode(schedule.getSourceAirport().getAirportCode())) {
            throw new RecordNotFound("Source Airport");
        }

        if (!airportDao.existsByAirportCode(schedule.getDestinationAirport().getAirportCode())) {
            throw new RecordNotFound("Destination Airport");
        }
    }
}
