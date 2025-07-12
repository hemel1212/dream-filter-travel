package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.travel.travel.entity.TransportBooking;

public interface TransportBookingRepository extends JpaRepository<TransportBooking, Long> {
	// Find by booking
	List<TransportBooking> findByBookingBookingId(Long bookingId);

	@Query("SELECT tb.seatPlan FROM TransportBooking tb WHERE tb.transport.transportId = :transportId")
	List<String> findSeatPlansByTransportId(@Param("transportId") Long transportId);
}
