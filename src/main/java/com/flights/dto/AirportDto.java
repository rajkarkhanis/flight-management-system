package com.flights.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AirportDto {

    @NotEmpty(message = "Airport code cannot be empty")
    @Size(message = "Airport code must be three characters", min = 3, max = 3)
    private String airportCode; // BOM, DEL,

    @NotEmpty(message = "Airport name cannot be empty")
    private String airportName;

    @NotEmpty(message = "Airport location cannot be empty")
    private String airportLocation;

    public AirportDto() {

    }
    public AirportDto(String airportCode, String airportName, String airportLocation) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.airportLocation = airportLocation;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public String toString() {
        return "AirportDto{" +
                "airportCode='" + airportCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", airportLocation='" + airportLocation + '\'' +
                '}';
    }

    public String getAirportLocation() {
        return airportLocation;
    }

    public void setAirportLocation(String airportLocation) {
        this.airportLocation = airportLocation;
    }
}
