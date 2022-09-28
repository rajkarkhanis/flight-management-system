package com.flights.bean;

import java.math.BigInteger;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
public class Passenger {
	
	@Id
	@GeneratedValue
	private int passengerId;
	BigInteger pnrNumber; 
	String passengerName;
	Integer passengerAge;
	BigInteger passengerUIN;
	Double Luggage;
	
	
	public Passenger(BigInteger pnrNumber, String passengerName, Integer passengerAge, BigInteger passengerUIN,
			Double luggage) {
		super();
		this.pnrNumber = pnrNumber;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerUIN = passengerUIN;
		Luggage = luggage;
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
	public BigInteger getPassengerUIN() {
		return passengerUIN;
	}
	public void setPassengerUIN(BigInteger passengerUIN) {
		this.passengerUIN = passengerUIN;
	}
	public Double getLuggage() {
		return Luggage;
	}
	public void setLuggage(Double luggage) {
		Luggage = luggage;
	}
	@Override
	public String toString() {
		return "Passenger [pnrNumber=" + pnrNumber + ", passengerName=" + passengerName + ", passengerAge="
				+ passengerAge + ", passengerUIN=" + passengerUIN + ", Luggage=" + Luggage + "]";
	}
}