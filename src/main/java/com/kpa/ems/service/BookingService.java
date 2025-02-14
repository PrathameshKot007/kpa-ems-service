package com.kpa.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpa.ems.dto.BookingResponseDTO;
import com.kpa.ems.entity.Booking;
import com.kpa.ems.entity.Event;
import com.kpa.ems.entity.UserEntity;
import com.kpa.ems.repository.BookingRepository;
import com.kpa.ems.repository.EventRepository;
import com.kpa.ems.repository.UserRepository;

@Service
public class BookingService {
	@Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;
    
    public Booking bookTickets(Long eventId, String userEmail, int numberOfSeats) {
    	Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        UserEntity user = userRepository.getUserByEmailId(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        
        //Check for enough available seats
        if(event.getAvailableSeats() < numberOfSeats) {
        	throw new RuntimeException("Not enough seats available.");
        }
        
        event.setAvailableSeats(event.getAvailableSeats()-numberOfSeats);
        eventRepository.save(event);
        
        // Generate unique ticket numbers
        List<String> ticketNumbers = generateTicketNumbers(numberOfSeats);
        
        Booking booking = new Booking();
        booking.setEvent(event);
        booking.setUser(user);
        booking.setNumberOfSeats(numberOfSeats);
        booking.setTicketNumbers(String.join(",", ticketNumbers));
        
        return bookingRepository.save(booking);
    }
    
    private List<String> generateTicketNumbers(int numberOfSeats) {
        List<String> ticketNumbers = new ArrayList<>();
        for (int i = 0; i < numberOfSeats; i++) {
            ticketNumbers.add("TICKET-" + UUID.randomUUID().toString());
        }
        return ticketNumbers;
    }
    
    public List<BookingResponseDTO> getBookingsByUser(String userEmail) {
        UserEntity user = userRepository.getUserByEmailId(userEmail)
            .orElseThrow(() -> new RuntimeException("User not found"));
        List<Booking> bookings = bookingRepository.findByUser(user);
        return bookings.stream()
		        .map(booking -> new BookingResponseDTO(
		                booking.getEvent().getEventId(),
		                booking.getNumberOfSeats(),
		                booking.getUser().getUserEmail(),
		                booking.getTicketNumbers()
		            ))
		            .collect(Collectors.toList());
    }
}
