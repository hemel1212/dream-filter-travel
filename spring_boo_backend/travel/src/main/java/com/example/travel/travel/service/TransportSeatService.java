package com.example.travel.travel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.travel.travel.DTO.CreateSeatDto;
import com.example.travel.travel.entity.Transport;
import com.example.travel.travel.entity.TransportSeat;
import com.example.travel.travel.repository.TransportRepository;
import com.example.travel.travel.repository.TransportSeatRepository;

@Service
public class TransportSeatService {

	private final TransportSeatRepository transportSeatRepository;
	private final TransportRepository repository;

	public TransportSeatService(TransportSeatRepository transportSeatRepository, TransportRepository repository) {
		this.transportSeatRepository = transportSeatRepository;
		this.repository = repository;
	}

	public TransportSeat createSeat(CreateSeatDto entity) {
		Transport transport = repository.findById(entity.getTransportId())
				.orElseThrow(() -> new RuntimeException("Transport not found by id: " + entity.getTransportId()));

		TransportSeat rSeat = new TransportSeat();
		rSeat.setSeatNumber(entity.getSeatNumber());
		rSeat.setTransport(transport);
		rSeat.setIsAvailable(true);
		return transportSeatRepository.save(rSeat);
	}

	public TransportSeat updateSeat(TransportSeat entity) {

		TransportSeat rSeat = transportSeatRepository.findById(entity.getSeatId()).orElseThrow(

				() -> new RuntimeException("Transport seat not found by id: " + entity.getSeatId())

		);

		rSeat.setIsAvailable(entity.getIsAvailable());
		rSeat.setSeatNumber(entity.getSeatNumber());

		return transportSeatRepository.save(rSeat);
	}

	public List<TransportSeat> getAllSeatByTransportId(Long id) {

		List<TransportSeat> aList = transportSeatRepository.findAllByTransport_TransportId(id);
		return aList;
	}

	public void deleteSeat(Long id) {

		transportSeatRepository.deleteById(id);

	}

}
