package com.flights.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flights.bean.Flight;

@Repository
public interface FlightDao extends JpaRepository<Flight,Integer>{

}
