package com.example.travel.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.travel.travel.entity.TourPackage;
import com.example.travel.travel.repository.TourPackageRepository;

@Service
public class TourPackageService {
	private final TourPackageRepository tourPackageRepository;

	public TourPackageService(TourPackageRepository tourPackageRepository) {
		this.tourPackageRepository = tourPackageRepository;
	}

	public List<TourPackage> getAllPackages() {
		return tourPackageRepository.findAll();
	}

	public Optional<TourPackage> getPackageById(Long packageId) {
		return tourPackageRepository.findById(packageId);
	}

	public TourPackage createPackage(TourPackage tourPackage) {
		return tourPackageRepository.save(tourPackage);
	}

	public TourPackage updatePackage(Long packageId, TourPackage updatedPackage) {
		updatedPackage.setPackageId(packageId);
		return tourPackageRepository.save(updatedPackage);
	}

	public void deletePackage(Long packageId) {
		tourPackageRepository.deleteById(packageId);
	}

	public List<TourPackage> searchByDestination(String destination) {
		return tourPackageRepository.findByDestinationContainingIgnoreCase(destination);
	}

	public void deleteTourPackage(Long id) {
		tourPackageRepository.deleteById(id);
		
	}

	public Object getTourPackageById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public Object createTourPackage(TourPackage tourPackage) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TourPackage> getAllTourPackages() {

		List<TourPackage> list = tourPackageRepository.findAll();
		
		return list;
	}

	public TourPackage updateTourPackage(Long id, TourPackage tourPackage) {

		tourPackage.setPackageId(id);
		
		return tourPackageRepository.save(tourPackage);
	}
}
