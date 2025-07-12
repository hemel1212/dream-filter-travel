package com.example.travel.travel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.CreateSeatDto;
import com.example.travel.travel.entity.TransportSeat;
import com.example.travel.travel.service.TransportSeatService;

@RestController
@RequestMapping("/seat")
public class TransportSeatControllar {

	private final TransportSeatService transportSeatService;

	public TransportSeatControllar(TransportSeatService transportSeatService) {
		this.transportSeatService = transportSeatService;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createSeat(@RequestBody CreateSeatDto entity) {

		TransportSeat seat = transportSeatService.createSeat(entity);

		return new ResponseEntity<>(seat, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateSeat(@RequestBody TransportSeat entity) {

		TransportSeat seat = transportSeatService.updateSeat(entity);

		return new ResponseEntity<>(seat, HttpStatus.OK);
	}

	@GetMapping("/get/all/{id}")
	public ResponseEntity<?> getAllSeatByTransportId(@PathVariable Long id) {

		List<TransportSeat> seat = transportSeatService.getAllSeatByTransportId(id);

		return new ResponseEntity<>(seat, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSeat(@RequestBody Long id) {

		transportSeatService.deleteSeat(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
