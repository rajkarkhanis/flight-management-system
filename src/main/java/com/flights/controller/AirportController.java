package com.flights.controller;

import com.flights.bean.Airport;
import com.flights.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/getAllAirport")
    public List<Airport> getAllAirports(){
        return airportService.viewAirport();
    }

    @GetMapping("/getAirport/{id}")
    public Optional<Airport> getAirport(@PathVariable("id") String airportCode){
        System.out.println(airportCode);
        return airportService.viewAirport(airportCode);
    }

}
