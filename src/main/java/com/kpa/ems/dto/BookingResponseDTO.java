package com.kpa.ems.dto;

public class BookingResponseDTO {
	private Long eventId;
    private int numberOfSeats;
    private String userEmail;
    private String ticketNumbers;

    public BookingResponseDTO(Long eventId, int numberOfSeats, String userEmail, String ticketNumbers) {
        this.eventId = eventId;
        this.numberOfSeats = numberOfSeats;
        this.userEmail = userEmail;
        this.ticketNumbers = ticketNumbers;
    }

    // Getters
    public Long getEventId() {
        return eventId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getUserEmail() {
        return userEmail;
    }

	public String getTicketNumbers() {
		return ticketNumbers;
	}

	public void setTicketNumbers(String ticketNumbers) {
		this.ticketNumbers = ticketNumbers;
	}
    
    
}
