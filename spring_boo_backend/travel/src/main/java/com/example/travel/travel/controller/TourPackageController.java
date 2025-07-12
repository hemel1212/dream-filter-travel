package com.example.travel.travel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.TourPacakgeResponseDTO;
import com.example.travel.travel.entity.TourPackage;
import com.example.travel.travel.service.TourPackageService;

@RestController
@RequestMapping("/api/tour-packages")
public class TourPackageController {

	@Autowired
	private TourPackageService tourPackageService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TourPackage> createPackage(@RequestBody TourPackage tourPackage) {
		return ResponseEntity.ok(tourPackageService.createPackage(tourPackage));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getPackage(@PathVariable Long id) {
		return ResponseEntity.ok(tourPackageService.getTourPackageById(id));
	}

	@GetMapping
	public ResponseEntity<List<TourPacakgeResponseDTO>> getAllPackages() {
		List<TourPackage> packageList = tourPackageService.getAllTourPackages();
		List<TourPacakgeResponseDTO> dtos = new ArrayList<>();
		
		for (TourPackage tourPackage: packageList) {
			TourPacakgeResponseDTO dto = new TourPacakgeResponseDTO();
			dto.setPackageId(tourPackage.getPackageId());
			dto.setName(tourPackage.getName());
			dto.setDescription(tourPackage.getDescription());
			dto.setPrice(tourPackage.getPrice());
			dto.setDurationDays(tourPackage.getDurationDays());
			dto.setDestination(tourPackage.getDestination());
			dto.setCreatedAt(tourPackage.getCreatedAt());
			
			dtos.add(dto);
		}
		
		return ResponseEntity.ok(dtos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TourPackage> updatePackage(@PathVariable Long id, @RequestBody TourPackage tourPackage) {
		TourPackage tour =tourPackageService.updateTourPackage(id, tourPackage);
		return ResponseEntity.ok(tour);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
		tourPackageService.deleteTourPackage(id);
		return ResponseEntity.noContent().build();
	}
}
