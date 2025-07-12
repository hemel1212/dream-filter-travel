package com.example.travel.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.entity.TourSchedule;
import com.example.travel.travel.repository.TourScheduleRepository;

@RestController
@RequestMapping("/api/tour-schedules")
@CrossOrigin
public class TourScheduleController {

    @Autowired
    private TourScheduleRepository repo;

    @GetMapping
    public List<TourSchedule> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public TourSchedule create(@RequestBody TourSchedule schedule) {
        return repo.save(schedule);
    }
}
