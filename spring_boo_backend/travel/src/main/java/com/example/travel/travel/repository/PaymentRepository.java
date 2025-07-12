package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	// Find payments by booking ID
	List<Payment> findByBookingBookingId(Long bookingId);
}