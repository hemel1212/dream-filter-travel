package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.TourPackage;

public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {
	// Optional: search by destination
	List<TourPackage> findByDestinationContainingIgnoreCase(String destination);
}