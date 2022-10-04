package com.flights.service;

import com.flights.bean.Airport;
import com.flights.dao.AirportDao;
import com.flights.dto.AirportDto;
import com.flights.exception.RecordNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Airport airport= airportDao.findByAirportCode(airportCode);
        if(airport==null){
            throw new RecordNotFound("Airport doesn't exist in the database");
        }
        return airport;
    }
    @Override
    public Airport addAirport(AirportDto airport){
        Airport newAirport= new Airport();
        newAirport.setAirportCode(airport.getAirportCode());
        newAirport.setAirportName(airport.getAirportName());
        newAirport.setAirportLocation(airport.getAirportLocation());
        airportDao.save(newAirport);
        return newAirport;
    }
}
