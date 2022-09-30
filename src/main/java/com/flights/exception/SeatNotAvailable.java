package com.flights.exception;

public class SeatNotAvailable extends Exception{
    public SeatNotAvailable (String message){
        super(message);
    }
}
