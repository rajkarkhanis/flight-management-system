package com.flights.dto;

import com.flights.bean.Passenger;
import com.flights.bean.ScheduledFlight;
import com.flights.bean.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookingDto {

//    private int bookingId;
    private LocalDate bookingDate;
    private List<PassengerDto> passengerList;
    private Double ticketCost;
    private int scheduledFlightId;
    private Integer noOfPassengers;

//    public int getBookingId() {
//        return bookingId;
//    }
//
//    public void setBookingId(int bookingId) {
//        this.bookingId = bookingId;
//    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List<PassengerDto> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<PassengerDto> passengerList) {
        this.passengerList = passengerList;
    }

    public Double getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(Double ticketCost) {
        this.ticketCost = ticketCost;
    }

    public int getScheduledFlightId() {
        return scheduledFlightId;
    }

    public void setScheduledFlight(int scheduledFlightId) {
        this.scheduledFlightId = scheduledFlightId;
    }

    public Integer getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(Integer noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }
}
