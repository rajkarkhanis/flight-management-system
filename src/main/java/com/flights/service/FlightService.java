package com.flights.service;

import java.math.BigInteger;
import java.util.List;

import com.flights.exception.InvalidDataEntry;
import com.flights.exception.RecordNotFound;
import org.springframework.stereotype.Service;

import com.flights.bean.Flight;
@Service
public interface FlightService {
   
	public Flight addFlight(Flight flight) throws InvalidDataEntry;
	
	 public Flight modifyFlight(Flight flight) throws RecordNotFound, InvalidDataEntry;
	 
	 public Flight viewFlight(BigInteger flightNumber)  throws RecordNotFound;
	 
	 public List<Flight> viewFlight();
	 
	 public void deleteFlight(BigInteger flightNumber) throws RecordNotFound;
	 
	 public void validateFlight(Flight flight) throws InvalidDataEntry;
	 
}
