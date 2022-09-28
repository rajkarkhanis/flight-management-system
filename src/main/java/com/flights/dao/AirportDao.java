package com.flights.dao;

import java.util.List;

import com.flights.bean.Airport;

public interface AirportDao{

	List<Airport> viewAirport(); // Returns a list of all airports
	
	Airport viewAirport(String airportCode); // Returns details of an airport identified by its airport code
}
