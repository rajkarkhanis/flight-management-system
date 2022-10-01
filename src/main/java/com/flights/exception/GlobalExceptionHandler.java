package com.flights.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecordNotFound.class)
    public ResponseEntity<?> recordNotFoundException(RecordNotFound ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatNotAvailable.class)
    public ResponseEntity<?> seatNotAvailableException(SeatNotAvailable ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(InvalidDateTime.class)
    public ResponseEntity<?> invalidDateTimeException(InvalidDateTime ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(InvalidPassengerUIN.class)
    public ResponseEntity<?> invalidPassengerUIN(InvalidPassengerUIN ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataEntry.class)
    public ResponseEntity<?> invalidDataEntry(InvalidDataEntry ex,WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAirport.class)
    public ResponseEntity<?> invalidAirport(InvalidAirport ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
