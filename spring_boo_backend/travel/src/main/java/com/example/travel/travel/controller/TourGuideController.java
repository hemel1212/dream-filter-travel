package com.example.travel.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.entity.TourGuide;
import com.example.travel.travel.repository.TourGuideRepository;

@RestController
@RequestMapping("/api/tour-guides")
@CrossOrigin(origins = "*")
public class TourGuideController {

    @Autowired
    private TourGuideRepository tourGuideRepository;

    @GetMapping
    public List<TourGuide> getAll() {
        return tourGuideRepository.findAll();
    }

    @PostMapping
    public TourGuide create(@RequestBody TourGuide tourGuide) {
        return tourGuideRepository.save(tourGuide);
    }
}
