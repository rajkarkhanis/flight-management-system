package com.flights.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class FlightDto {

    private BigInteger flightId;
    @NotEmpty(message = "Flight Model cannot be empty")
    private String flightModel;
    @NotEmpty(message = "Flight Model cannot be empty")
    private String carrierName;
    @NotNull(message = "Seat capacity cannot be null")@DecimalMin(value = "1", message = "Seat capacity should be greater than 0")
    private Integer seatCapacity;

    public FlightDto() {
    }

    public FlightDto(BigInteger flightId, String flightModel, String carrierName, Integer seatCapacity) {
        this.flightId = flightId;
        this.flightModel = flightModel;
        this.carrierName = carrierName;
        this.seatCapacity = seatCapacity;
    }

    public FlightDto(String flightModel, String carrierName, Integer seatCapacity) {
        this.flightModel = flightModel;
        this.carrierName = carrierName;
        this.seatCapacity = seatCapacity;
    }

    public BigInteger getFlightId(){
        return flightId;
    }
    public String getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(String flightModel) {
        this.flightModel = flightModel;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }


}
