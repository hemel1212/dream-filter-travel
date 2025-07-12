package com.example.travel.travel.service.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.travel.travel.entity.Booking;

public interface BookingService {
	List<Booking> getAllBookings();

	Optional<Booking> getBookingById(Long bookingId);

	Booking createBooking(Booking booking);

	Booking updateBooking(Long bookingId, Booking updatedBooking);

	void deleteBooking(Long bookingId);

	List<Booking> getBookingsByCustomer(Long customerId);
}
