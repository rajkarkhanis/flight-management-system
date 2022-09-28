package com.flights.dao;

import com.flights.bean.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledFlightDao extends JpaRepository<ScheduledFlight, Integer> {

}
