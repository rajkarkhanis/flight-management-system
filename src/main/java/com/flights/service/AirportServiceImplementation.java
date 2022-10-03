package com.flights.service;

import com.flights.bean.Airport;
import com.flights.bean.User;
import com.flights.dao.AirportDao;
import com.flights.exception.RecordNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class AirportServiceImplementation implements AirportService{

    @Autowired
    AirportDao airportDao;

    @Override
    public List<Airport> viewAirport(){
        return airportDao.findAll();
    }
    @Override
    public Airport viewAirport(String airportCode) throws RecordNotFound{
        Supplier s= ()->new RecordNotFound("Airport doesn't exist in the database");
        return airportDao.findById(airportCode).orElseThrow(s);
    }
}
