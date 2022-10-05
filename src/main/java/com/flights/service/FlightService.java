package com.flights.service;

import java.math.BigInteger;
import java.util.List;

import com.flights.dto.FlightDto;
import com.flights.exception.RecordNotFound;
import org.springframework.stereotype.Service;

import com.flights.bean.Flight;
@Service
public interface FlightService {
   
	public Flight addFlight(FlightDto flight);
	
	 public Flight modifyFlight(FlightDto flight) throws RecordNotFound,NullPointerException;
	 
	 public Flight viewFlight(BigInteger flightNumber)  throws RecordNotFound;
	 
	 public List<Flight> viewFlight();
	 
	 public void deleteFlight(BigInteger flightNumber) throws RecordNotFound;

	 
}
