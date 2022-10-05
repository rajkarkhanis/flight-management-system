package com.flights.controller;

import com.flights.bean.Airport;
import com.flights.dto.AirportDto;
import com.flights.exception.RecordNotFound;
import com.flights.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/getAllAirport")
    public List<Airport> getAllAirports(){
        return airportService.viewAirport();
    }

    @GetMapping("/getAirport/{id}")
    public Airport getAirport(@PathVariable("id") String airportCode) throws RecordNotFound {
        return airportService.viewAirport(airportCode);
    }


}
