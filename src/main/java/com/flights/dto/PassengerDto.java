package com.flights.dto;

import javax.validation.constraints.*;
import java.math.BigInteger;

public class PassengerDto {

    @NotNull( message = "pnr Number cannot be null")
    private BigInteger pnrNumber;
    @NotBlank( message = "Passenger cannot be blank")
    @Size(message = "Passenger name must contain 2 to 32 characters", min=2,max=32)
    private String passengerName;
    @NotNull(message = "Passenger age cannot be null")@DecimalMin(value="0",message = "age should be greater than 0")
    @DecimalMax(value="150",message = "Age should be less than 150")
    private Integer passengerAge;
    @NotBlank( message = "Passenger UIN cannot be null")
    @Size(min=12,max=12,message = "passenger UIN must have 12 digits")
    private String passengerUIN;
    @NotNull(message = "Passenger luggage cannot be null")
    private Double luggage;


    public BigInteger getPnrNumber() {
        return pnrNumber;
    }

    public void setPnrNumber(BigInteger pnrNumber) {
        this.pnrNumber = pnrNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(Integer passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getPassengerUIN() {
        return passengerUIN;
    }

    public void setPassengerUIN(String passengerUIN) {
        this.passengerUIN = passengerUIN;
    }

    public Double getLuggage() {
        return luggage;
    }

    public void setLuggage(Double luggage) {
        this.luggage = luggage;
    }




}
