package com.example.travel.travel.service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.travel.travel.entity.Booking;
import com.example.travel.travel.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	private final BookingRepository bookingRepository;

	public BookingServiceImpl(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public Optional<Booking> getBookingById(Long bookingId) {
		return bookingRepository.findById(bookingId);
	}

	@Override
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public Booking updateBooking(Long bookingId, Booking updatedBooking) {
		updatedBooking.setBookingId(bookingId);
		return bookingRepository.save(updatedBooking);
	}

	@Override
	public void deleteBooking(Long bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	@Override
	public List<Booking> getBookingsByCustomer(Long customerId) {
		return bookingRepository.findByCustomerCustomerId(customerId);
	}
}
