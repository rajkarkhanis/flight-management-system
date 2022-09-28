package com.flights.service;

import java.math.BigInteger;
import java.util.List;

import com.flights.bean.Flight;

public interface FlightService {
   
	public Flight addFlight(Flight flight);
	
	 public Flight modifyFlight(Flight flight);
	 
	 public Flight viewFlight(BigInteger flightNumber);
	 
	 public List<Flight> viewFlight();
	 
	 public void deleteFlight(BigInteger flightNumber);
	 
	 public void validateFlight(Flight flight);
	 
}
