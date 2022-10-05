package com.flights.dto;


import lombok.Data;


import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BookingDto {
    @NotNull(message = "userType cannot be empty")
    private List<PassengerDto> passengerList;
    @NotNull(message = "Ticket cost cannot be null")
    private Double ticketCost;
    @NotNull(message = "Scheduled Flight Id cannot be null")
    private int scheduledFlightId;



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

}
