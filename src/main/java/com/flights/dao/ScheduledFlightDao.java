package com.flights.dao;

import com.flights.bean.Airport;
import com.flights.bean.Flight;
import com.flights.bean.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    String query = "SELECT sf " +
            "FROM ScheduledFlight sf " +
            "WHERE sf.schedule.sourceAirport = :source " +
            "AND sf.schedule.destinationAirport = :destination " +
            "AND (date(sf.schedule.departureTime) = :date OR date(sf.schedule.arrivalTime) = :date)";
    @Query(value = query)
    List<ScheduledFlight> getScheduledFlights(@Param("source") Airport source, @Param("destination") Airport destination, @Param("date") LocalDate date);
}
