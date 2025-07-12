package com.example.travel.travel.service;

import org.springframework.stereotype.Service;

import com.example.travel.travel.entity.Transport;
import com.example.travel.travel.repository.TransportRepository;

@Service
public class TransportService {
	private final TransportRepository transportRepository;

	public TransportService(TransportRepository transportRepository) {
		this.transportRepository = transportRepository;
	}
	
	public Transport updateTransport(Long transportId, Transport updateTransport) {
		updateTransport.setTransportId(transportId);
		return transportRepository.save(updateTransport);
	}

	public void deleteTransport(Long transportId) {
		transportRepository.deleteById(transportId);
	}
}
