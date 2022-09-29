package com.flights.dao;

import com.flights.bean.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledFlightDao extends JpaRepository<ScheduledFlight, Integer> {

}
