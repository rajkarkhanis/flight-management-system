package com.flights.utils;

import com.flights.bean.Flight;
import com.flights.bean.Schedule;

/*
Wrapper class for updating ScheduledFlight
Used in AdminController modifyScheduledFlight(Flight, Schedule, Integer)
 */
public class FlightScheduleWrapper {
    private Flight flight;
    private Schedule schedule;
    private Integer flightId;

    public FlightScheduleWrapper() {
    }

    public FlightScheduleWrapper(Flight flight, Schedule schedule, Integer flightId) {
        this.flight = flight;
        this.schedule = schedule;
        this.flightId = flightId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }
}
