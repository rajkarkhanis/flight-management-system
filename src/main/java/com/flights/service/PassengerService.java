package com.flights.service;

import com.flights.bean.Passenger;
import com.flights.dto.PassengerDto;
import org.springframework.stereotype.Service;

@Service
public interface PassengerService {

    Passenger createPassenger(PassengerDto passengerDto);
}
