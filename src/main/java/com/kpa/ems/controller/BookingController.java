package com.kpa.ems.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpa.ems.dto.BookingRequest;
import com.kpa.ems.dto.BookingResponseDTO;
import com.kpa.ems.entity.Booking;
import com.kpa.ems.service.BookingService;
import com.kpa.ems.util.JwtUtil;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
    private BookingService bookingService;
	
	@Autowired
	private JwtUtil jwtUtil;

    @PostMapping("/book")
    public ResponseEntity<?> bookTickets(@RequestBody BookingRequest bookingRequest) {
        try {
            Booking booking = bookingService.bookTickets(bookingRequest.getEventId(), bookingRequest.getUserEmail(), bookingRequest.getNumberOfSeats());
            return ResponseEntity.ok("Congratulations! Your tickets are booked, here are the ticket numbers - "+booking.getTicketNumbers()); // Return the booking details (including ticket numbers)
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // Handle errors
        }
    }
    
    @GetMapping("/my-bookings")
    public ResponseEntity<?> getMyBookings(@RequestHeader("Authorization") String token){
    	try {
    		String userEmail = jwtUtil.extractUsername(token.substring(7));
    		List<BookingResponseDTO> bookings = bookingService.getBookingsByUser(userEmail);
    		return ResponseEntity.ok(bookings);
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error retrieving bookings: " + e.getMessage());
    	}
    }
    
    
}
