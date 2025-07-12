package com.example.travel.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.ItineraryDTO;
import com.example.travel.travel.entity.Itinerary;
import com.example.travel.travel.entity.TourGuide;
import com.example.travel.travel.repository.ItineraryRepository;
import com.example.travel.travel.repository.TourPackageRepository;

@RestController
@RequestMapping("/api/itineraries")
@CrossOrigin(origins = "*")
public class ItineraryController {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private TourPackageRepository tourPackageRepository;

    @GetMapping
    public List<Itinerary> getAll() {
        return itineraryRepository.findAll();
    }

    @PostMapping
    public Itinerary create(@RequestBody ItineraryDTO dto) {
        Itinerary itinerary = new Itinerary();
        itinerary.setTourPackage(tourPackageRepository.findById(dto.getPackageId()).orElseThrow());
        itinerary.setDayNumber(dto.getDayNumber());
        itinerary.setDescription(dto.getDescription());
        itinerary.setLocation(dto.getLocation());
        return itineraryRepository.save(itinerary);
    }
}

