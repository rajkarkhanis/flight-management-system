package com.flights.bean;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Component
@Table
public class Airport {
	
	@Id
	@GeneratedValue
	private int airportId;
	private String airportCode; // BOM, DEL, 
	private String airportName;
	private String airportLocation;
	public Airport() {
		
	}
	public Airport(String airportCode, String airportName, String airportLocation) {
		super();
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
	public String getAirportLocation() {
		return airportLocation;
	}
	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}
	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airportCode=" + airportCode + ", airportName=" + airportName
				+ ", airportLocation=" + airportLocation + "]";
	}
	
	
	
	
	
}
