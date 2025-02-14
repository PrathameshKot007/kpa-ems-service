package com.kpa.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ems_bookings")
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "eventId")
    private Event event;  // Event the user booked
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserEntity user; // The customer who booked the tickets
    
    private int numberOfSeats; // Number of seats the customer booked
    private String ticketNumbers; // List of generated ticket numbers (Comma separated)
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public String getTicketNumbers() {
		return ticketNumbers;
	}
	public void setTicketNumbers(String ticketNumbers) {
		this.ticketNumbers = ticketNumbers;
	}
	public long getBookingId() {
		return bookingId;
	}
    
    
}
