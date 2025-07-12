package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.TourGuide;

public interface TourGuideRepository extends JpaRepository<TourGuide, Long> {
	// Optional search
	List<TourGuide> findByNameContainingIgnoreCase(String name);
}
