package com.flights.bean;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Entity
@Component
@Table
public class Booking {
	
	@Id
	@GeneratedValue
	private int bookingId;
	@ManyToOne
	private User userId;
	private LocalDate bookingDate;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Passenger> passengerList;
	private Double ticketCost;
	@ManyToOne(cascade=CascadeType.ALL)
	private ScheduledFlight scheduledFlight;
	
	public Booking() {
		super();
	}
	
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
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
	public ScheduledFlight getScheduledFlight() {
		return scheduledFlight;
	}
	public void setScheduledFlight(ScheduledFlight scheduledFlight) {
		this.scheduledFlight = scheduledFlight;
	}
	public Integer getNoOfPassengers() {
		return noOfPassengers;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", userId=" + userId + ", bookingDate=" + bookingDate
				+ ", passengerList=" + passengerList + ", ticketCost=" + ticketCost + ", flight=" + scheduledFlight
				+ ", noOfPassengers=" + noOfPassengers + "]";
	}

	public void setNoOfPassengers(Integer noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public int getBookingId() {
		return bookingId;
	}
	public Booking(User userId, LocalDate bookingDate, List<Passenger> passengerList,
			Double ticketCost, ScheduledFlight scheduledFlight, Integer noOfPassengers) {
		super();
		this.userId = userId;
		this.bookingDate = bookingDate;
		this.passengerList = passengerList;
		this.ticketCost = ticketCost;
		this.scheduledFlight = scheduledFlight;
		this.noOfPassengers = noOfPassengers;
	}
	private Integer noOfPassengers;


    public void setBookingId(int i) {
		bookingId = i;
    }
}
