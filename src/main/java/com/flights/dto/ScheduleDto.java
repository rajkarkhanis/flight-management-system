package com.flights.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ScheduleDto {

    private int scheduleId;
    @NotBlank(message = "Source Airport cannot be empty")
    @Size(message = "Source airport must have 3 characters",min=3,max=3)
    private String sourceAirportCode;
    @NotBlank(message = "Destination Airport cannot be empty")
    @Size(message = "Destination airport must have 3 characters",min=3,max=3)
    private String destinationAirportCode;
    @NotNull(message = "arrival Time cannot be null")
    @Future(message = "Arrival date time should be a Future Date Time ")
    private LocalDateTime arrivalTime;
    @NotNull(message = "Departure Time cannot be null")
    @Future(message = "Departure date time should be a Future Date Time ")
    private LocalDateTime departureTime;

    public ScheduleDto() {
    }

    public ScheduleDto(String sourceAirportCode, String destinationAirportCode, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.sourceAirportCode = sourceAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSourceAirportCode() {
        return sourceAirportCode;
    }

    public void setSourceAirportCode(String sourceAirportCode) {
        this.sourceAirportCode = sourceAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
}
