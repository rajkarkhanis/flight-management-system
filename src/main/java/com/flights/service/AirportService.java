package com.flights.service;

import java.util.List;
import com.flights.bean.Airport;
import com.flights.dto.AirportDto;
import com.flights.exception.RecordNotFound;

public interface AirportService {
	
    List<Airport> viewAirport();

    Airport viewAirport(String airportCode) throws RecordNotFound;

    Airport addAirport(AirportDto airport);

}
