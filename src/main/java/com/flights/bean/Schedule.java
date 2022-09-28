package com.flights.bean;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
public class Schedule {

    @Id @GeneratedValue
    private int id;
    @Autowired
    private Airport sourceAirport;
    @Autowired
    private Airport destinationAirport;
    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;

    public Schedule() {
    }

    public Schedule(Airport sourceAirport, Airport destinationAirport, ZonedDateTime departureTime, ZonedDateTime arrivalTime) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
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

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public ZonedDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ZonedDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", sourceAirport=" + sourceAirport +
                ", destinationAirport=" + destinationAirport +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
