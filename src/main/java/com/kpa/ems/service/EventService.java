package com.kpa.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpa.ems.entity.Event;
import com.kpa.ems.entity.UserEntity;
import com.kpa.ems.repository.EventRepository;
import com.kpa.ems.repository.UserRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Event createEvent(Event event, String adminEmail) {
		UserEntity admin = userRepository.getUserByEmailId(adminEmail).orElseThrow(() -> new RuntimeException("Admin not found"));
		if(admin.getUserType().equalsIgnoreCase("CUSTOMER")) {
			throw new RuntimeException("Only admins can create events.");
		}
		
		event.setCreatedBy(admin);
		return eventRepository.save(event);
	}
	
	 /**
     * Get all events (Accessible to both Admin and Customer)
     */
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
