package com.kpa.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kpa.ems.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	
}
