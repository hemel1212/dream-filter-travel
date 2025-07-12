package com.example.travel.travel.service.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.travel.travel.entity.TourPackage;

public interface TourPackageService {
	List<TourPackage> getAllPackages();

	Optional<TourPackage> getPackageById(Long packageId);

	TourPackage createPackage(TourPackage tourPackage);

	TourPackage updatePackage(Long packageId, TourPackage updatedPackage);

	void deletePackage(Long packageId);

	List<TourPackage> searchByDestination(String destination);
}
