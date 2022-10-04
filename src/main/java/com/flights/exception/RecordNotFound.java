package com.flights.exception;

public class RecordNotFound extends Exception{
    public RecordNotFound(String message){
        super("Record of "+ message.substring("Class ".length()) + "does not exist");
    }
}

