package com.example.travel.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.HotelDTO;
import com.example.travel.travel.entity.Hotel;
import com.example.travel.travel.repository.HotelBookingRepository;
import com.example.travel.travel.repository.HotelRepository;
import com.example.travel.travel.service.HotelService;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelBookingRepository hotelBookingRepository;

    @GetMapping
    public List<HotelDTO> getAll() {
        List<Hotel> all = hotelRepository.findAll();

        List<HotelDTO> list = all.stream()
                .map(Hotel::toHotelDto)
                .toList();
        return list;
    }

//    @PostMapping
//    public Hotel create(@RequestBody Hotel hotel) {
//        return hotelRepository.save(hotel);
//    }

    @PostMapping
    public Hotel createHotel(@RequestBody HotelDTO dto) {
        return hotelService.createHotel(dto);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hotelRepository.deleteById(id);
    }
}

