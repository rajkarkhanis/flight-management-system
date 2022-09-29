package com.flights.service;

import com.flights.bean.Airport;
import com.flights.dao.AirportDao;
import java.util.List;
import java.util.Optional;

public class AirportServiceImplementation implements AirportService{

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
