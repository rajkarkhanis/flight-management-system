package com.flights.service;

import java.util.List;
import java.util.Optional;

import com.flights.bean.Airport;

public interface AirportService {
	
public List<Airport> viewAirport();

public Optional<Airport> viewAirport(String airportCode);
	
}
