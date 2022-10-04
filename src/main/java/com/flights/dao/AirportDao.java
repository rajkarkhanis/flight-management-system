package com.flights.dao;


import com.flights.bean.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDao extends JpaRepository<Airport,Integer> {
    Airport findByAirportCode(String sourceAirportCode);

    boolean existsByAirportCode(String airportCode);

}
