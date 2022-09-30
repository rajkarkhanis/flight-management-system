package com.flights.controller;

import com.flights.bean.Airport;
import com.flights.bean.Flight;
import com.flights.bean.Schedule;
import com.flights.bean.ScheduledFlight;
import com.flights.service.FlightService;
import com.flights.service.ScheduledFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    FlightService flightService;

    @Autowired
    ScheduledFlightService scheduledFlightService;

    @PostMapping(value = "/addFlight")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight addedFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(addedFlight, HttpStatus.OK);
    }

    @GetMapping(value = "/viewFlight")
    public ResponseEntity<List<Flight>> viewFlight() {
        List<Flight> flightList = flightService.viewFlight();
        return new ResponseEntity<>(flightList, HttpStatus.OK);
    }

    @GetMapping(value = "/viewFlight/{flightNumber}")
    public ResponseEntity<Flight> viewFlight(@PathVariable BigInteger flightNumber) {
        Flight flight = flightService.viewFlight(flightNumber);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PutMapping(value = "/modifyFlight")
    public ResponseEntity<Flight> modifyFlight(@RequestBody Flight flight) {
        Flight modifiedFlight = flightService.modifyFlight(flight);
        return new ResponseEntity<>(modifiedFlight, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteFlight/{flightNumber}")
    public ResponseEntity<String> deleteFlight(@PathVariable BigInteger flightNumber) {
        flightService.deleteFlight(flightNumber);
        String message = "Flight Deleted Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(value = "/scheduleFlight")
    public ResponseEntity<ScheduledFlight> scheduleFlight(@RequestBody ScheduledFlight scheduledFlight) {
        ScheduledFlight addedFlight = scheduledFlightService.scheduleFlight(scheduledFlight);
        return new ResponseEntity<>(addedFlight, HttpStatus.OK);
    }

    @GetMapping(value = "/viewScheduledFlights")
    public ResponseEntity<List<ScheduledFlight>> viewScheduledFlights(@RequestBody Airport source, @RequestBody Airport destination, @RequestBody LocalDate date) {
        List<ScheduledFlight> scheduledFlightList = scheduledFlightService.viewScheduledFlights(source, destination, date);
        return new ResponseEntity<>(scheduledFlightList, HttpStatus.OK);
    }

    @GetMapping(value = "/viewScheduledFlights")
    public ResponseEntity<List<ScheduledFlight>> viewScheduledFlights() {
        List<ScheduledFlight> scheduledFlightList = scheduledFlightService.viewScheduledFlight();
        return new ResponseEntity<>(scheduledFlightList, HttpStatus.OK);
    }

    @GetMapping(value = "/viewScheduledFlights/{flightNumber}")
    public ResponseEntity<List<ScheduledFlight>> viewScheduledFlights(@PathVariable BigInteger flightNumber) {
        List<ScheduledFlight> scheduledFlightList = scheduledFlightService.viewScheduledFlights(flightNumber);
        return new ResponseEntity<>(scheduledFlightList, HttpStatus.OK);
    }

    @PutMapping(value = "/modifyScheduledFlight")
    public ResponseEntity<ScheduledFlight> modifyScheduledFlight(@RequestBody Flight flight, @RequestBody Schedule schedule, @RequestBody Integer flightId) {
        ScheduledFlight scheduledFlight = scheduledFlightService.modifyScheduledFlight(flight, schedule, flightId);
        return new ResponseEntity<>(scheduledFlight, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteScheduledFlight/{flightNumber}")
    public ResponseEntity<String> deleteScheduledFlight(@PathVariable BigInteger flightNumber) {
        scheduledFlightService.deleteScheduledFlight(flightNumber);
        String message = "ScheduledFlight Deleted Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
