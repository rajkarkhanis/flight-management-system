package com.flights.bean;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Entity
@Component
public class Flight {
	@Id
	@GeneratedValue
	private int flightId;
	private BigInteger flightNumber;
	private String flightModel;
	private String carrierName;
	private Integer seatCapacity;
	public Flight() {
		
	}
	public Flight(int flightId,BigInteger flightNumber, String flightModel, String carrierName, Integer seatCapacity) {
		super();
		this.flightId=flightId;
		this.flightNumber = flightNumber;
		this.flightModel = flightModel;
		this.carrierName = carrierName;
		this.seatCapacity = seatCapacity;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
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
		return "Flight [flightId=" + flightId + ", flightNumber=" + flightNumber + ", flightModel=" + flightModel
				+ ", carrierName=" + carrierName + ", seatCapacity=" + seatCapacity + "]";
	}
}
