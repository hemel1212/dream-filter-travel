package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.HotelBooking;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {
	// Find by booking ID
	List<HotelBooking> findByBookingBookingId(Long bookingId);
	
}
