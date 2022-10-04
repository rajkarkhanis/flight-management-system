package com.flights.dao;

import com.flights.bean.Booking;
import com.flights.bean.User;
import com.flights.exception.RecordNotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {

    @Query("select b from Booking b WHERE b.userId=:user")
    List<Booking> findByUserId(User user) throws RecordNotFound;
}
