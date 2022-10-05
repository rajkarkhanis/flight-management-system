package com.flights.service;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

import com.flights.dto.FlightDto;
import com.flights.exception.InvalidDataEntry;
import com.flights.exception.RecordNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.flights.bean.Flight;
import com.flights.dao.FlightDao;
@Service @RequiredArgsConstructor
public class FlightServiceImplementation implements FlightService {

	private final FlightDao dao;
	@Override
	public Flight addFlight(FlightDto flight) throws InvalidDataEntry{
		Flight newFlight = new Flight(flight.getFlightModel(), flight.getCarrierName(), flight.getSeatCapacity());
		dao.save(newFlight);
		return newFlight;
	}

	@Override
	public Flight modifyFlight(FlightDto flight) throws RecordNotFound, InvalidDataEntry,NullPointerException{
		if(flight.getFlightId()==null){
			throw new NullPointerException("Flight Id cannot be null");
		}
		BigInteger id=flight.getFlightId();
		Supplier s1=()->new RecordNotFound("Flight does not exist in Database");
		Flight modifiedFlight=dao.findById(id).orElseThrow(s1);
		modifiedFlight.setFlightModel(flight.getFlightModel());
		modifiedFlight.setCarrierName(flight.getCarrierName());
		modifiedFlight.setSeatCapacity(flight.getSeatCapacity());
		dao.save(modifiedFlight);
		return modifiedFlight;
	}

	@Override
	public Flight viewFlight(BigInteger flightNumber) throws RecordNotFound{
		Supplier s1=()->new RecordNotFound("Flight does not exist in database");
		return dao.findById(flightNumber).orElseThrow(s1);


	}

	@Override
	public List<Flight> viewFlight() {
		return dao.findAll();

	}

	@Override
	public void deleteFlight(BigInteger flightNumber) throws RecordNotFound {
		Supplier s1=()->new RecordNotFound("Flight does not exist in database");
		dao.findById(flightNumber).orElseThrow(s1);
		dao.deleteById(flightNumber);
	}
}
