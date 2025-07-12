package com.example.travel.travel.controller;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.HotelBookingDTO;
import com.example.travel.travel.DTO.HotelBookingResponseDto;
import com.example.travel.travel.entity.Customer;
import com.example.travel.travel.entity.Hotel;
import com.example.travel.travel.entity.HotelBooking;
import com.example.travel.travel.repository.BookingRepository;
import com.example.travel.travel.repository.CustomerRepository;
import com.example.travel.travel.repository.HotelBookingRepository;
import com.example.travel.travel.repository.HotelRepository;

@RestController
@RequestMapping("/api/hotel-bookings")
@CrossOrigin(origins = "*")
public class HotelBookingController {

    @Autowired
    private HotelBookingRepository hotelBookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<HotelBookingResponseDto> getAll() {
        List<HotelBooking> getAllBooking= hotelBookingRepository.findAll();
        
        return getAllBooking.stream().map(this:: mapHotelBookingResponseDto).toList();
    }

    private HotelBookingResponseDto mapHotelBookingResponseDto(HotelBooking booking) {
    	
    	HotelBookingResponseDto dto =  new HotelBookingResponseDto();
    	dto.setCustomerName(booking.getCustomer().getName());
    	dto.setCustomerEmail(booking.getCustomer().getEmail());
    	dto.setCheckInDate(booking.getCheckInDate());
    	dto.setNumRooms(booking.getNumRooms());
    	dto.setCheckOutDate(booking.getCheckOutDate());
		
		return dto;
	}

	@PostMapping
    public HotelBookingResponseDto create(@RequestBody HotelBookingDTO dto) {
    	
    	Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(
    			()-> new RuntimeException("Customer not found"));
    	
    	Hotel hotel = hotelRepository.findById(dto.getHotelId()).orElseThrow(
    			()-> new RuntimeException("Hotel not found"));
    	
        HotelBooking booking = new HotelBooking();
        booking.setCustomer(customer);
        booking.setHotel(hotel);
        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());
        booking.setNumRooms(dto.getNumRooms());
        
        HotelBooking saveHotelBooking = hotelBookingRepository.save(booking);
        
        HotelBookingResponseDto hBRD = new HotelBookingResponseDto();
        hBRD.setCustomerName(saveHotelBooking.getCustomer().getName());
        hBRD.setCustomerEmail(saveHotelBooking.getCustomer().getEmail());
        hBRD.setNumRooms(saveHotelBooking.getNumRooms());
        hBRD.setCheckInDate(saveHotelBooking.getCheckInDate());
     

        return hBRD;
    }
}

