package com.flights.bean;

import java.math.BigInteger;

import javax.persistence.*;

import org.springframework.stereotype.Component;
@Entity
@Component
public class Flight {
	@Id
	@GeneratedValue
	private BigInteger flightNumber;
	@Column(unique=true)
	private String flightModel;
	private String carrierName;
	private Integer seatCapacity;
	public Flight() {
		
	}
	public Flight(String flightModel, String carrierName, Integer seatCapacity) {
		super();
		this.flightModel = flightModel;
		this.carrierName = carrierName;
		this.seatCapacity = seatCapacity;
	}
	
	
	public Integer getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	public String getCarrierName() {
		return carrierName;
	}
	
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getFlightModel() {
		return flightModel;
	}
	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}
	public BigInteger getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(BigInteger flightNumber) {
		this.flightNumber = flightNumber;
	}
	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", flightModel=" + flightModel + ", carrierName=" + carrierName
				+ ", seatCapacity=" + seatCapacity + "]";
	}
	
}
