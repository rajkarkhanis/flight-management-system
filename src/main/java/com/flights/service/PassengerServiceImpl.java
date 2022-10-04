package com.flights.service;

import com.flights.bean.Passenger;
import com.flights.dto.PassengerDto;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements  PassengerService {

    @Override
    public Passenger createPassenger(PassengerDto passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setPassengerAge(passengerDto.getPassengerAge());
        passenger.setPassengerName(passengerDto.getPassengerName());
        passenger.setPassengerUIN(passengerDto.getPassengerUIN());
        passenger.setLuggage(passengerDto.getLuggage());
        return passenger;
    }
}
