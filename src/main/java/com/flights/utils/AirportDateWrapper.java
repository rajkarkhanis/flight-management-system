package com.flights.utils;

import com.flights.bean.Airport;

import java.time.LocalDate;

/*
Class to wrap over two airports, and date object.
Used as a request body in AdminController viewScheduledFlights(Airport, Airport, LocalDate)
 */
public class AirportDateWrapper {
    private Airport source;
    private Airport destination;
    private LocalDate date;

    public AirportDateWrapper() {
    }

    public AirportDateWrapper(Airport source, Airport destination, LocalDate date) {
        this.source = source;
        this.destination = destination;
        this.date = date;
    }

    public Airport getSource() {
        return source;
    }

    public void setSource(Airport source) {
        this.source = source;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
