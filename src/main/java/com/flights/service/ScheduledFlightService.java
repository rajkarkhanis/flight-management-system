package com.flights.service;

import com.flights.bean.Airport;
import com.flights.bean.Flight;
import com.flights.bean.Schedule;
import com.flights.bean.ScheduledFlight;
import com.flights.exception.SeatNotAvailable;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public interface ScheduledFlightService {
    // Schedule a flight along with time, location, and capacity
    ScheduledFlight scheduleFlight(ScheduledFlight scheduledFlight);

    // Return list of flights between two airports on a given date
    List<ScheduledFlight> viewScheduledFlights(Airport firstAirport, Airport secondAirport, LocalDate localDate);

    // Return list of scheduled flights by flight number
    List<ScheduledFlight> viewScheduledFlights(BigInteger flightNumber);

    // Show all details and status of all flights
    List<ScheduledFlight> viewScheduledFlight();

    // Modify details of a scheduled flight
    ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule, Integer flightId);

    // Remove flight from available flights
    void deleteScheduledFlight(BigInteger flightNumber);

    // Validate attributes of scheduled flight
    void validateScheduledFlight(ScheduledFlight scheduledFlight) throws SeatNotAvailable;
}
