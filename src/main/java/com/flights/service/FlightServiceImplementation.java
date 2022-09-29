package com.flights.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flights.bean.Flight;
import com.flights.dao.FlightDao;
@Service
public class FlightServiceImplementation implements FlightService {
    @Autowired
	FlightDao dao;
	@Override
	public Flight addFlight(Flight flight) {
		dao.save(flight);
		return flight;
	}

	@Override
	public Flight modifyFlight(Flight flight) {
		BigInteger id=flight.getFlightNumber();
		Flight flight1=dao.findById(id).orElseThrow();
		flight1.setFlightNumber(flight.getFlightNumber());
		flight1.setFlightModel(flight.getFlightModel());
		flight1.setCarrierName(flight.getCarrierName());
		flight1.setSeatCapacity(flight.getSeatCapacity());
		dao.save(flight1);
		return flight1;
	}

	@Override
	public Flight viewFlight(BigInteger flightNumber) {
		Optional<Flight> f=dao.findById(flightNumber);
		if(f.isPresent())
			return f.get();
		else
			return null;
		//Flight f1=(Flight) f.get();
	    //return f1;
		

	}

	@Override
	public List<Flight> viewFlight() {
		List<Flight>fl=dao.findAll();
		return fl;
		
	}

	@Override
	public void deleteFlight(BigInteger flightNumber) {

		Optional<Flight> f=dao.findById(flightNumber);
		if(f.isPresent())
			dao.deleteById(flightNumber);
	}

	@Override
	public void validateFlight(Flight flight) {
		
	}

}
