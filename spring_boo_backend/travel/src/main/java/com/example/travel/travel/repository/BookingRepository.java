package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travel.travel.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	// Find bookings by customer
	List<Booking> findByCustomerCustomerId(Long customerId);
}