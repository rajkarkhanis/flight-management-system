package com.flights.bean;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class ScheduledFlight {

    @Id @GeneratedValue
    private int scheduledFlightId;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Flight flight;

    private int availableSeats;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Schedule schedule;

    public ScheduledFlight(){

    }
    public ScheduledFlight(Flight flight, int availableSeats, Schedule schedule) {
        this.flight = flight;
        this.availableSeats = availableSeats;
        this.schedule = schedule;
    }

    public int getScheduledFlightId() {
        return scheduledFlightId;
    }

    public void setScheduledFlightId(int scheduledFlightId) {
        this.scheduledFlightId = scheduledFlightId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
