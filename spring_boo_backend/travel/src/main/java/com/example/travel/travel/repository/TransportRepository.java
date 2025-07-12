package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.Transport;

public interface TransportRepository extends JpaRepository<Transport, Long> {
	// Optional search
	List<Transport> findByType(String type);
}
