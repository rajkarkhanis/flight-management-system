package com.flights.bean;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

public class Schedule {
    @Id @GeneratedValue
    private int scheduleId;
    @Autowired
    private Airport sourceAirport;
    @Autowired
    private Airport destinationAirport;
    private ZonedDateTime arrivalTime;
    private ZonedDateTime departureTime;

    public Schedule() {
    }

    public Schedule(Airport sourceAirport, Airport destinationAirport, ZonedDateTime arrivalTime, ZonedDateTime departureTime) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public ZonedDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ZonedDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", sourceAirport=" + sourceAirport +
                ", destinationAirport=" + destinationAirport +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
