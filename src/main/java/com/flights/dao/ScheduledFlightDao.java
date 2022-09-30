package com.flights.dao;

import com.flights.bean.Airport;
import com.flights.bean.Flight;
import com.flights.bean.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduledFlightDao extends JpaRepository<ScheduledFlight, Integer> {
    // Return ScheduledFlight object based on class member Flight
    ScheduledFlight getScheduledFlightByFlight(Flight flight);

    // Custom query to get ScheduledFlight objects between two airports on a given date
    // @param source : Airport = first airport
    // @param destination : Airport = second airport
    // @param date : LocalDate = date of schedule
    String query = "SELECT sf from ScheduledFlight sf WHERE" +
            "sf.Schedule.sourceAirport :source " +
            "AND sf.Schedule.destinationAirport :destination " +
            "AND (date(sf.Schedule.departureTime) = :date OR date(sf.Schedule.arrivalTime) = :date)";
    @Query(value = query)
    List<ScheduledFlight> getScheduledFlights(Airport source, Airport destination, LocalDate date);
}
