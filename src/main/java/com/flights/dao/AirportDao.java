package com.flights.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flights.bean.Airport;

public interface AirportDao extends JpaRepository<Airport, Integer>{

}
