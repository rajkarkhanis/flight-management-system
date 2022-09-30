package com.flights.bean;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.stereotype.Component;


@Entity
@Component
@Table
public class Booking {
	
	@Id
	@GeneratedValue
	private int bookingId;
	@ManyToOne
	private User userId;
	private Date bookingDate;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Passenger> passengerList;
	private Double ticketCost;
	@ManyToOne(cascade=CascadeType.ALL)
	private Flight flight;
	
	public Booking() {
		super();
	}
	
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	public Double getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(Double ticketCost) {
		this.ticketCost = ticketCost;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Integer getNoOfPassengers() {
		return noOfPassengers;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", userId=" + userId + ", bookingDate=" + bookingDate
				+ ", passengerList=" + passengerList + ", ticketCost=" + ticketCost + ", flight=" + flight
				+ ", noOfPassengers=" + noOfPassengers + "]";
	}

	public void setNoOfPassengers(Integer noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public int getBookingId() {
		return bookingId;
	}
	public Booking(int bookingId, User userId, Date bookingDate, List<Passenger> passengerList,
			Double ticketCost, Flight flight, Integer noOfPassengers) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.bookingDate = bookingDate;
		this.passengerList = passengerList;
		this.ticketCost = ticketCost;
		this.flight = flight;
		this.noOfPassengers = noOfPassengers;
	}
	private Integer noOfPassengers;
	
	
	
}
