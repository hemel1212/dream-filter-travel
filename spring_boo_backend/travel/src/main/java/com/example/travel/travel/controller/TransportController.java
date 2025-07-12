package com.example.travel.travel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.entity.Transport;
import com.example.travel.travel.entity.TransportBooking;
import com.example.travel.travel.repository.TransportBookingRepository;
import com.example.travel.travel.repository.TransportRepository;
import com.example.travel.travel.service.TransportService;

@RestController
@RequestMapping("/api/transports")
@CrossOrigin(origins = "*")
public class TransportController {

	private final TransportRepository transportRepository;
	private final TransportBookingRepository transportBookingRepository;

	private final TransportService transportService;

	public TransportController(TransportRepository transportRepository, TransportService transportService,
			TransportBookingRepository transportBookingRepository) {
		this.transportRepository = transportRepository;
		this.transportService = transportService;
		this.transportBookingRepository = transportBookingRepository;
	}

	@GetMapping
	public List<Transport> getAll() {
		return transportRepository.findAll();
	}

	@PostMapping
	public Transport create(@RequestBody Transport transport) {
		return transportRepository.save(transport);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransport(@PathVariable Long id) {
		transportService.deleteTransport(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/all/bookings")
	public List<TransportBooking> getBookingSeats() {
		return transportBookingRepository.findAll();
	}

}
