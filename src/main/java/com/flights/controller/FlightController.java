package com.flights.controller;

import java.math.BigInteger;
import java.util.List;

import com.flights.dto.FlightDto;
import com.flights.exception.InvalidDataEntry;
import com.flights.exception.RecordAlreadyExists;
import com.flights.exception.RecordNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flights.bean.Flight;
import com.flights.service.FlightService;

@RestController
@RequestMapping("api")
public class FlightController {
	@Autowired
	FlightService flightservice;
	
	
	@PostMapping("addflight")
	public Flight addFlight(@RequestBody FlightDto flightDto) throws InvalidDataEntry, RecordAlreadyExists {
		Flight newFlight = new Flight(
				flightDto.getFlightModel(),
				flightDto.getCarrierName(),
				flightDto.getSeatCapacity()
		);
		return flightservice.addFlight(newFlight);
		
	}
	@PutMapping(path="/updateflight")
	public Flight modifyFlight(@RequestBody FlightDto flightDto) throws RecordNotFound, InvalidDataEntry {
		Flight newFlight = new Flight(
				flightDto.getFlightModel(),
				flightDto.getCarrierName(),
				flightDto.getSeatCapacity()
		);
		return flightservice.modifyFlight(newFlight);
	}
	@GetMapping("viewflightbyflightnumber/{flightNumber}")
	public Flight viewFlight(@PathVariable BigInteger flightNumber) throws RecordNotFound {
		return flightservice.viewFlight(flightNumber);
	}
	
	@GetMapping(path="viewflight")
	public List<Flight> viewFlight()
	{
		return flightservice.viewFlight();
	}
	
	@DeleteMapping(path="/deleteflight/{flightNumber}") // ..variable name should be given
	public void deleteFlight(@PathVariable BigInteger flightNumber) throws RecordNotFound {
		flightservice.deleteFlight(flightNumber);
		
	}

	
	
	
	
}
