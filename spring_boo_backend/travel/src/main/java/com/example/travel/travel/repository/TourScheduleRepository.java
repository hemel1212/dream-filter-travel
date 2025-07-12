package com.example.travel.travel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.TourSchedule;

public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {
	// Find schedule by guide
	List<TourSchedule> findByTourGuideGuideId(Long guideId);

	// Find schedule by package
	List<TourSchedule> findByTourPackagePackageId(Long packageId);
}
