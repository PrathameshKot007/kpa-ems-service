package com.kpa.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpa.ems.entity.Event;
import com.kpa.ems.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@PostMapping("/addEvent")
	public ResponseEntity<?> addEvent(@RequestBody Event event, @AuthenticationPrincipal UserDetails userDetails){
		System.out.println("Signed in user is "+userDetails.getUsername());
		try {
		Event createEvent = eventService.createEvent(event, userDetails.getUsername());
		return ResponseEntity.ok(createEvent);
		}catch(RuntimeException ex) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
	    }
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Event>> getAllEvents() {
		return ResponseEntity.ok(eventService.getAllEvents());
	}
}
