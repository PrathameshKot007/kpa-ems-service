package com.kpa.ems.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eventId;

	private String eventName;

	private String eventLocation;
	private String eventDateTime;
	private String eventTicketPrice;

	private int availableSeats; // Total seats available for this event

	@OneToMany(mappedBy = "event")
	private Set<Booking> bookings;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private UserEntity createdBy; // Admin who created the event

	public UserEntity getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserEntity createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToMany(mappedBy = "registeredEvents")
	private Set<UserEntity> registeredUsers = new HashSet<>();

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(String eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public String getEventTicketPrice() {
		return eventTicketPrice;
	}

	public void setEventTicketPrice(String eventTicketPrice) {
		this.eventTicketPrice = eventTicketPrice;
	}

	public long getEventId() {
		return eventId;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public Set<UserEntity> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(Set<UserEntity> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

}
