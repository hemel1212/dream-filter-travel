package com.example.travel.travel.service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.travel.travel.entity.TourPackage;
import com.example.travel.travel.repository.TourPackageRepository;

@Service
public class TourPackageServiceImpl implements TourPackageService {
	private final TourPackageRepository tourPackageRepository;

	public TourPackageServiceImpl(TourPackageRepository tourPackageRepository) {
		this.tourPackageRepository = tourPackageRepository;
	}

	@Override
	public List<TourPackage> getAllPackages() {
		return tourPackageRepository.findAll();
	}

	@Override
	public Optional<TourPackage> getPackageById(Long packageId) {
		return tourPackageRepository.findById(packageId);
	}

	@Override
	public TourPackage createPackage(TourPackage tourPackage) {
		return tourPackageRepository.save(tourPackage);
	}

	@Override
	public TourPackage updatePackage(Long packageId, TourPackage updatedPackage) {
		updatedPackage.setPackageId(packageId);
		return tourPackageRepository.save(updatedPackage);
	}

	@Override
	public void deletePackage(Long packageId) {
		tourPackageRepository.deleteById(packageId);
	}

	@Override
	public List<TourPackage> searchByDestination(String destination) {
		return tourPackageRepository.findByDestinationContainingIgnoreCase(destination);
	}
}
