package com.kpa.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kpa.ems.entity.Booking;
import com.kpa.ems.entity.UserEntity;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUser(UserEntity user);
}
