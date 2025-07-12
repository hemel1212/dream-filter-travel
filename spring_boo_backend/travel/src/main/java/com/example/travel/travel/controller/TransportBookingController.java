package com.example.travel.travel.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.TransportBookingDTO;
import com.example.travel.travel.entity.Transport;
import com.example.travel.travel.entity.TransportBooking;
import com.example.travel.travel.repository.BookingRepository;
import com.example.travel.travel.repository.TransportBookingRepository;
import com.example.travel.travel.repository.TransportRepository;

@RestController
@RequestMapping("/api/transport-bookings")
@CrossOrigin(origins = "*")
public class TransportBookingController {

	@Autowired
	private TransportBookingRepository transportBookingRepository;

	@Autowired
	private TransportRepository transportRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@GetMapping
	public List<TransportBookingDTO> getAll() {
		return transportBookingRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<TransportBookingDTO> create(@RequestBody TransportBookingDTO dto) {
		TransportBooking saved = transportBookingRepository.save(convertToEntity(dto));
		return new ResponseEntity<>(convertToDTO(saved), HttpStatus.CREATED);
	}

	@GetMapping("/seat-plans/{transportId}")
	public ResponseEntity<String> getUniqueSeatPlans(@PathVariable Long transportId) {
		List<String> seatPlans = transportBookingRepository.findSeatPlansByTransportId(transportId);

		Set<String> uniqueSeats = new LinkedHashSet<>();

		for (String plan : seatPlans) {
			if (plan != null && !plan.trim().isEmpty()) {
				String[] seats = plan.split(",");
				for (String seat : seats) {
					uniqueSeats.add(seat.trim());
				}
			}
		}

		String result = String.join(",", uniqueSeats);
		return ResponseEntity.ok(result);
	}

	private TransportBookingDTO convertToDTO(TransportBooking booking) {
		TransportBookingDTO dto = new TransportBookingDTO();
		dto.setTransportBookingId(booking.getTransportBookingId());

		if (booking.getTransport() != null) {
			dto.setTransportId(booking.getTransport().getTransportId());
		}
		dto.setSeatPlan(booking.getSeatPlan());
		dto.setDepartureDate(booking.getDepartureDate());
		dto.setArrivalDate(booking.getArrivalDate());
		dto.setNumSeats(booking.getNumSeats());

		return dto;
	}

	private TransportBooking convertToEntity(TransportBookingDTO dto) {
		TransportBooking entity = new TransportBooking();
		entity.setTransportBookingId(dto.getTransportBookingId());

		Transport transport = transportRepository.findById(dto.getTransportId())
				.orElseThrow(() -> new RuntimeException("Transport not found"));

		entity.setBooking(null);
		entity.setTransport(transport);
		entity.setSeatPlan(dto.getSeatPlan());
		entity.setDepartureDate(dto.getDepartureDate());
		entity.setArrivalDate(dto.getArrivalDate());

		if (transport.getAvailableSeat() < dto.getNumSeats()) {
			throw new RuntimeException("Seat not available as your need!!");
		}
		entity.setNumSeats(dto.getNumSeats());
		transport.setAvailableSeat(transport.getAvailableSeat() - dto.getNumSeats());
		transportRepository.save(transport);
		entity.setStatus(dto.getStatus());
		return entity;
	}

}
