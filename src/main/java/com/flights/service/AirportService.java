package com.flights.service;

import java.util.List;
import com.flights.bean.Airport;

public interface AirportService {
	
public List<Airport> viewAirport();

public Airport viewAirport(String airportCode);
	
}
