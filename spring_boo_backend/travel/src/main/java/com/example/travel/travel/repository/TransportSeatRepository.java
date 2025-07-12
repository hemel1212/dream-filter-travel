package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travel.travel.entity.TransportSeat;

@Repository
public interface TransportSeatRepository extends JpaRepository<TransportSeat, Long> {
	List<TransportSeat> findAllByTransport_TransportId(Long transportId);

	List<TransportSeat> findByTransport_TransportIdAndIsAvailableTrue(Long transportId);

}
