package com.flights.bean;

import javax.persistence.*;

@Entity
@Table
public class Airport {
	
	@Id
	@GeneratedValue
	private Integer airportId;
	@Column(unique = true)
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
	public void setAirportId(Integer airportId) {
		this.airportId = airportId;
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
	public Integer getAirportId(){return this.airportId;}
	@Override
	public String toString() {
		return "Airport [ airportId= "+airportId+" airportCode=" + airportCode + ", airportName=" + airportName
				+ ", airportLocation=" + airportLocation + "]";
	}
	
	
	
	
	
}
