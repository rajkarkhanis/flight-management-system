package com.flights.service;

import java.util.List;
import java.util.Optional;

import com.flights.bean.Airport;
import com.flights.exception.RecordNotFound;

public interface AirportService {
	
public List<Airport> viewAirport();

public Airport viewAirport(String airportCode) throws RecordNotFound;
	
}
