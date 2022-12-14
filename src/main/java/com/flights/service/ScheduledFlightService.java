package com.flights.service;

import com.flights.bean.Airport;
import com.flights.bean.Flight;
import com.flights.bean.Schedule;
import com.flights.bean.ScheduledFlight;
import com.flights.exception.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface ScheduledFlightService {
    // Schedule a flight along with time, location, and capacity
    ScheduledFlight scheduleFlight(ScheduledFlight scheduledFlight) throws SeatNotAvailable, RecordAlreadyExists, InvalidDateTime, RecordNotFound;

    // Return list of flights between two airports on a given date
    List<ScheduledFlight> viewScheduledFlights(Airport firstAirport, Airport secondAirport, LocalDate localDate);

    // Return list of scheduled flights by flight number
    List<ScheduledFlight> viewScheduledFlights(BigInteger flightNumber) throws RecordNotFound;

    // Show all details and status of all flights
    List<ScheduledFlight> viewScheduledFlight();

    // Modify details of a scheduled flight
    ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule, Integer flightId) throws RecordNotFound;

    // Remove flight from available flights
    void deleteScheduledFlight(BigInteger flightNumber) throws RecordNotFound;

    // Validate attributes of scheduled flight
    void validateScheduledFlight(ScheduledFlight scheduledFlight) throws SeatNotAvailable, RecordAlreadyExists, InvalidDateTime, RecordNotFound;
}
