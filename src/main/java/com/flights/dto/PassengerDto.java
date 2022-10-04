package com.flights.dto;

import java.math.BigInteger;

public class PassengerDto {

    private int passengerId;
    private BigInteger pnrNumber;
    private String passengerName;
    private Integer passengerAge;
    private String passengerUIN;
    private Double luggage;

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

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
