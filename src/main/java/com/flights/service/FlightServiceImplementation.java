package com.flights.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import com.flights.bean.ScheduledFlight;
import com.flights.exception.InvalidDataEntry;
import com.flights.exception.RecordAlreadyExists;
import com.flights.exception.RecordNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flights.bean.Flight;
import com.flights.dao.FlightDao;
@Service
public class FlightServiceImplementation implements FlightService {
    @Autowired
	FlightDao dao;
	@Override
	public Flight addFlight(Flight flight) throws InvalidDataEntry, RecordAlreadyExists {
		this.validateFlight(flight);
		List<Flight>flightList=dao.findAll();
		for(Flight x:flightList) {
			if (x.getFlightModel().equals(flight.getFlightModel())) {
				throw new RecordAlreadyExists("Flight already exists");
			}
		}
		dao.save(flight);
		return flight;
	}

	@Override
	public Flight modifyFlight(Flight flight) throws RecordNotFound, InvalidDataEntry{
		BigInteger id=flight.getFlightNumber() ;
		Supplier s1=()->new RecordNotFound("Flight does not exist in Database");
		Flight flight1=dao.findById(id).orElseThrow(s1);
		this.validateFlight(flight);
		flight1.setFlightNumber(flight.getFlightNumber());
		flight1.setFlightModel(flight.getFlightModel());
		flight1.setCarrierName(flight.getCarrierName());
		flight1.setSeatCapacity(flight.getSeatCapacity());
		dao.save(flight1);
		return flight1;
	}

	@Override
	public Flight viewFlight(BigInteger flightNumber) throws RecordNotFound{
		Supplier s1=()->new RecordNotFound("Flight does not exist in database");
		Flight flight=dao.findById(flightNumber).orElseThrow(s1);
		return flight;
		//Flight f1=(Flight) f.get();
	    //return f1;
		

	}

	@Override
	public List<Flight> viewFlight() {
		List<Flight>flightList=dao.findAll();
		return flightList;
		
	}

	@Override
	public void deleteFlight(BigInteger flightNumber) throws RecordNotFound {
		Supplier s1=()->new RecordNotFound("Flight does not exist in database");
		Flight f=dao.findById(flightNumber).orElseThrow(s1);
		dao.deleteById(flightNumber);
	}

	@Override
	public void validateFlight(Flight flight) throws InvalidDataEntry {
		if(flight.getSeatCapacity()<=0)
			throw new InvalidDataEntry("The seat capacity must be greater than zero");
		if(flight.getFlightModel()==null || flight.getFlightModel().isEmpty())
			throw new InvalidDataEntry("The Flight Model must not be NULL");
		if(flight.getCarrierName()==null || flight.getCarrierName().isEmpty())
			throw  new InvalidDataEntry("The CarrierName must not be null");

	}

}
