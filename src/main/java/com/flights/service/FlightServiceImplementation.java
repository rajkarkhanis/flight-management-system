package com.flights.service;

import java.math.BigInteger;
import java.util.List;

import com.flights.dto.FlightDto;
import com.flights.exception.RecordNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.flights.bean.Flight;
import com.flights.dao.FlightDao;
@Service @RequiredArgsConstructor
public class FlightServiceImplementation implements FlightService {

	private final FlightDao dao;
	@Override
	public Flight addFlight(FlightDto flight){
		Flight newFlight = new Flight(flight.getFlightModel(), flight.getCarrierName(), flight.getSeatCapacity());
		dao.save(newFlight);
		return newFlight;
	}

	@Override
	public Flight modifyFlight(FlightDto flight) throws RecordNotFound,NullPointerException{
		if(flight.getFlightId()==null){
			throw new NullPointerException("Flight Id cannot be null");
		}
		BigInteger id=flight.getFlightId();
		Flight modifiedFlight=dao.findById(id).orElseThrow(()-> new RecordNotFound(Flight.class.toString()));
		modifiedFlight.setFlightModel(flight.getFlightModel());
		modifiedFlight.setCarrierName(flight.getCarrierName());
		modifiedFlight.setSeatCapacity(flight.getSeatCapacity());
		dao.save(modifiedFlight);
		return modifiedFlight;
	}

	@Override
	public Flight viewFlight(BigInteger flightNumber) throws RecordNotFound{
		return dao.findById(flightNumber).orElseThrow(()-> new RecordNotFound(Flight.class.toString()));
	}

	@Override
	public List<Flight> viewFlight() {
		return dao.findAll();

	}

	@Override
	public void deleteFlight(BigInteger flightNumber) throws RecordNotFound {
		dao.findById(flightNumber).orElseThrow(()-> new RecordNotFound(Flight.class.toString()));
		dao.deleteById(flightNumber);
	}
}
