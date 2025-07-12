package com.example.travel.travel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.BookingDTO;
import com.example.travel.travel.DTO.BookingResponseDTO;
import com.example.travel.travel.DTO.TourPacakgeResponseDTO;
import com.example.travel.travel.entity.Booking;
import com.example.travel.travel.entity.Customer;
import com.example.travel.travel.entity.TourPackage;
import com.example.travel.travel.repository.BookingRepository;
import com.example.travel.travel.repository.CustomerRepository;
import com.example.travel.travel.repository.TourPackageRepository;
import com.example.travel.travel.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TourPackageRepository tourPackageRepository;
	@Autowired
	private BookingRepository bookingRepository;

//	@PostMapping
//	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
//		return ResponseEntity.ok(bookingService.createBooking(booking));
//	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBooking(@PathVariable Long id) {
		
		 Booking booking =  bookingService.getBookingById(id);
		 
		 return ResponseEntity.ok(booking);
		 
	}

	@GetMapping
	public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
		List<Booking> bookingList = bookingService.getAllBookings();
		List<BookingResponseDTO> dtos = new ArrayList<>();
		
		for (Booking booking: bookingList) {
			
			BookingResponseDTO dto = new BookingResponseDTO();
			dto.setCustomerId(booking.getCustomer().getCustomerId());
			dto.setBookingId(booking.getBookingId());
			dto.setPackageId(booking.getTourPackage().getPackageId());
			dto.setNumPeople(booking.getNumPeople());
			dto.setTotalAmount(booking.getTotalAmount());
			dto.setStatus(booking.getStatus());
			
			dtos.add(dto);
		}
			
		return ResponseEntity.ok(dtos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
		return ResponseEntity.ok(bookingService.updateBooking(id, booking));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BookingDTO dto) {
	    Optional<Customer> optionalCustomer = customerRepository.findById(dto.getCustomerId());
	    if (optionalCustomer.isEmpty()) {
	        return ResponseEntity.badRequest().body("Customer not found with ID: " + dto.getCustomerId());
	    }

	    Optional<TourPackage> optionalPackage = tourPackageRepository.findById(dto.getPackageId());
	    if (optionalPackage.isEmpty()) {
	        return ResponseEntity.badRequest().body("Tour package not found with ID: " + dto.getPackageId());
	    }

	    Booking booking = new Booking();
	    booking.setCustomer(optionalCustomer.get());
	    booking.setTourPackage(optionalPackage.get());
	    booking.setBookingDate(dto.getBookingDate());
	    booking.setStartDate(dto.getStartDate());
	    booking.setEndDate(dto.getEndDate());
	    booking.setNumPeople(dto.getNumPeople());
	    booking.setStatus(dto.getStatus());
	    booking.setTotalAmount(dto.getTotalAmount());

	    Booking saved = bookingRepository.save(booking);
	    return ResponseEntity.ok(saved);
	}

	
	
	// update
	@PutMapping("/{id}/status")
	public ResponseEntity<Booking> updateStatus(
	    @PathVariable Long id,
	    @RequestParam String status
	) {
	    Booking updated = bookingService.updateBookingStatus(id, status);
	    return ResponseEntity.ok(updated);
	}

}
