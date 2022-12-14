package com.flights.controller;

import com.flights.bean.*;
import com.flights.dao.FlightDao;
import com.flights.dao.ScheduleDao;
import com.flights.dto.*;
import com.flights.exception.*;
import com.flights.exception.RecordNotFound;
import com.flights.service.*;
import com.flights.utils.AirportDateWrapper;
import com.flights.utils.FlightScheduleWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final FlightService flightService;

    private final ScheduledFlightService scheduledFlightService;

    private final FlightDao flightDao;

    private final ScheduleDao scheduleDao;

    private final ScheduleService scheduleService;
    private final AirportService airportService;

    private final BookingService bookingService;
    //    USER
    private final UserService userService;

    @PostMapping(value = "/addFlight")
    public ResponseEntity<Flight> addFlight(@RequestBody @Valid FlightDto flight) {
        Flight addedFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(addedFlight, HttpStatus.OK);
    }

    @GetMapping(value = "/viewFlight")
    public ResponseEntity<List<Flight>> viewFlight() {
        List<Flight> flightList = flightService.viewFlight();
        return new ResponseEntity<>(flightList, HttpStatus.OK);
    }

    @GetMapping(value = "/viewFlight/{flightNumber}")
    public ResponseEntity<Flight> viewFlight(@PathVariable BigInteger flightNumber) throws RecordNotFound {
        Flight flight = flightService.viewFlight(flightNumber);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PutMapping(value = "/modifyFlight")
    public ResponseEntity<Flight> modifyFlight(@RequestBody  @Valid FlightDto flight) throws RecordNotFound {
        Flight modifiedFlight = flightService.modifyFlight(flight);
        return new ResponseEntity<>(modifiedFlight, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteFlight/{flightNumber}")
    public ResponseEntity<String> deleteFlight(@PathVariable BigInteger flightNumber) throws RecordNotFound {
        flightService.deleteFlight(flightNumber);
        String message = "Flight Deleted Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

//    Add Schedule
    @PostMapping(value = "/addSchedule")
    public ResponseEntity<Schedule> addFlight(@RequestBody  @Valid ScheduleDto scheduleDto) {
        Schedule newSchedule = scheduleService.addSchedule(scheduleDto);
        return new ResponseEntity<>(newSchedule, HttpStatus.OK);
    }

    @GetMapping(value = "/viewSchedules")
    public ResponseEntity<List<Schedule>> viewSchedule() {
        List<Schedule> scheduleList = scheduleService.viewSchedule();
        return new ResponseEntity<>(scheduleList, HttpStatus.OK);
    }

    @GetMapping(value = "/viewSchedule/{scheduleId}")
    public ResponseEntity<Schedule> viewFlight(@PathVariable int scheduleId) throws RecordNotFound {
        Schedule schedule = scheduleService.viewSchedule(scheduleId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PutMapping(value = "/modifySchedule")
    public ResponseEntity<Schedule> modifySchedule(@RequestBody @Valid ScheduleDto scheduleDto) throws RecordNotFound {
        Schedule schedule = scheduleService.modifySchedule(scheduleDto);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteSchedule/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable int scheduleId) throws RecordNotFound {
        scheduleService.deleteSchedule(scheduleId);
        String message = "Schedule deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(value = "/scheduleFlight")
    public ResponseEntity<ScheduledFlight> scheduleFlight(@RequestBody  @Valid ScheduledFlightDto scheduledFlightDto) throws SeatNotAvailable, RecordAlreadyExists, InvalidDateTime, RecordNotFound {
Flight flight = flightDao.findByFlightNumber(scheduledFlightDto.getFlightNumber());
        ScheduledFlight newScheduledFlight = new ScheduledFlight(
                flight,
                flight.getSeatCapacity(),
                scheduleDao.findByScheduleId(scheduledFlightDto.getScheduleId())
        );
        scheduledFlightService.scheduleFlight(newScheduledFlight);
        return new ResponseEntity<>(newScheduledFlight, HttpStatus.OK);
    }

    @GetMapping(value = "/viewScheduledFlightsOnDate")
    public ResponseEntity<List<ScheduledFlight>> viewScheduledFlights(@RequestBody  @Valid AirportDateWrapper wrapper) {
        Airport source = wrapper.getSource();
        Airport destination = wrapper.getDestination();
        LocalDate date = wrapper.getDate();
        List<ScheduledFlight> scheduledFlightList = scheduledFlightService.viewScheduledFlights(source, destination, date);
        return new ResponseEntity<>(scheduledFlightList, HttpStatus.OK);
    }

    @GetMapping(value = "/viewScheduledFlights")
    public ResponseEntity<List<ScheduledFlight>> viewScheduledFlights() {
        List<ScheduledFlight> scheduledFlightList = scheduledFlightService.viewScheduledFlight();
        return new ResponseEntity<>(scheduledFlightList, HttpStatus.OK);
    }

    @GetMapping(value = "/viewScheduledFlights/{flightNumber}")
    public ResponseEntity<List<ScheduledFlight>> viewScheduledFlightsById(@PathVariable BigInteger flightNumber) throws RecordNotFound {
        List<ScheduledFlight> scheduledFlightList = scheduledFlightService.viewScheduledFlights(flightNumber);
        return new ResponseEntity<>(scheduledFlightList, HttpStatus.OK);
    }

    @PutMapping(value = "/modifyScheduledFlight")
    public ResponseEntity<ScheduledFlight> modifyScheduledFlight(@RequestBody  @Valid FlightScheduleWrapper wrapper) throws RecordNotFound {
        Flight flight = wrapper.getFlight();
        Schedule schedule = wrapper.getSchedule();
        Integer flightId = wrapper.getFlightId();
        ScheduledFlight scheduledFlight = scheduledFlightService.modifyScheduledFlight(flight, schedule, flightId);
        return new ResponseEntity<>(scheduledFlight, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteScheduledFlight/{flightNumber}")
    public ResponseEntity<String> deleteScheduledFlight(@PathVariable BigInteger flightNumber) throws RecordNotFound {
        scheduledFlightService.deleteScheduledFlight(flightNumber);
        String message = "ScheduledFlight Deleted Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // BOOKING CONTROLS

    @GetMapping("/getBookingById/{id}")
    public Booking getBookingById(@PathVariable("id") int bookingId) throws RecordNotFound {
        return bookingService.viewBooking(bookingId);
    }

    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings() {
        return bookingService.viewBooking();
    }

    // USER CONTROLS

    @GetMapping("getuser/{id}")
    public User viewUser(@PathVariable("id") int userId) throws RecordNotFound {
        BigInteger bi = BigInteger.valueOf(userId);
        return userService.viewUser(bi);

    }

    @GetMapping("getallusers")
    public List<User> viewUser() {
        return userService.viewUser();
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody @Valid UserDto user) throws RecordNotFound, InvalidEmail, InvalidPhoneNumber{
        return userService.updateUser(user);
    }

    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int userId) throws RecordNotFound{
        BigInteger bi = BigInteger.valueOf(userId);
        userService.deleteUser(bi);
    }

    @PostMapping("/addAirport")
    public Airport addAirport(@RequestBody AirportDto airport){
        return airportService.addAirport(airport);
    }
}
