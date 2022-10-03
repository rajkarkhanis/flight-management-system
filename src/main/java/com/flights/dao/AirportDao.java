package com.flights.dao;

//import java.util.List;

import com.flights.bean.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDao extends JpaRepository<Airport,String> {
    Airport findByAirportCode(String sourceAirportCode);
//
//	List<Airport> viewAirport(); // Returns a list of all airports
//
//	Airport viewAirport(String airportCode); // Returns details of an airport identified by its airport code
}
