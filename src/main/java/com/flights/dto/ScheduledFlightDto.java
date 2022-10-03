package com.flights.dto;

import com.flights.bean.Flight;
import com.flights.bean.Schedule;

import java.math.BigInteger;

public class ScheduledFlightDto {

    private int scheduledFlightId;
    private BigInteger flightNumber;
    private int availableSeats;
    private int scheduleId;

    public int getScheduledFlightId() {
        return scheduledFlightId;
    }

    public void setScheduledFlightId(int scheduledFlightId) {
        this.scheduledFlightId = scheduledFlightId;
    }

    public BigInteger getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(BigInteger flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
