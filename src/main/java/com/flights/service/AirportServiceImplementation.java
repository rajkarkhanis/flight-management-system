package com.flights.service;

import com.flights.bean.Airport;
import com.flights.dao.AirportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImplementation implements AirportService{

    @Autowired
    AirportDao airportDao;

    @Override
    public List<Airport> viewAirport(){
        return airportDao.findAll();
    }
    @Override
    public Optional<Airport> viewAirport(String airportCode) {
        return airportDao.findById(airportCode);
    }
}
