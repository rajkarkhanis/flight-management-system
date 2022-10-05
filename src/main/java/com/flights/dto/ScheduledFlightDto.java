package com.flights.dto;


import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class ScheduledFlightDto {
    @NotNull( message = "Flight Number cannot be null")
    private BigInteger flightNumber;
    @NotNull( message = "Schedule Id cannot be null")
    private int scheduleId;

    public BigInteger getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(BigInteger flightNumber) {
        this.flightNumber = flightNumber;
    }


    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
