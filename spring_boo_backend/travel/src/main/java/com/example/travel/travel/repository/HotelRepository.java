package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	// Search by location
	List<Hotel> findByLocationContainingIgnoreCase(String location);
}