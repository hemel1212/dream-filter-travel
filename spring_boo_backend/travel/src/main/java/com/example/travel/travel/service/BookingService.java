package com.example.travel.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.travel.travel.entity.Booking;
import com.example.travel.travel.repository.BookingRepository;

@Service
public class BookingService {
	private final BookingRepository bookingRepository;

	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public Booking getBookingById(Long bookingId) {
		Booking booking = bookingRepository.findById(bookingId).orElse(null);
		 
		 return booking;
	}

	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	public Booking updateBooking(Long bookingId, Booking updatedBooking) {
		
		//Booking booking = bookingRepository.findById(bookingId).orElse(null);
		updatedBooking.setBookingId(bookingId);
		
		return bookingRepository.save(updatedBooking);
	}

	public void deleteBooking(Long bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	public List<Booking> getBookingsByCustomer(Long customerId) {
		return bookingRepository.findByCustomerCustomerId(customerId);
	}
	
	
	//update
	public Booking updateBookingStatus(Long bookingId, String status) {
	    Booking booking = bookingRepository.findById(bookingId)
	        .orElseThrow(() -> new RuntimeException("Booking not found"));

	    booking.setStatus(status.toUpperCase());
	    return bookingRepository.save(booking);
	}
	
}
