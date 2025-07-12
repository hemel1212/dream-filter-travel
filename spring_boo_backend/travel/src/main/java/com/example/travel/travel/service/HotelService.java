package com.example.travel.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.travel.travel.DTO.HotelDTO;
import com.example.travel.travel.entity.Hotel;
import com.example.travel.travel.repository.HotelRepository;

@Service
public class HotelService {
	private final HotelRepository hotelRepository;

	public HotelService(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	public Optional<Hotel> getHotelById(Long hotelId) {
		return hotelRepository.findById(hotelId);
	}

	public Hotel createHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	public Hotel updateHotel(Long hotelId, Hotel updatedHotel) {
		updatedHotel.setHotelId(hotelId);
		return hotelRepository.save(updatedHotel);
	}

	public void deleteHotel(Long hotelId) {
		hotelRepository.deleteById(hotelId);
	}

	public List<Hotel> searchHotelsByLocation(String location) {
		return hotelRepository.findByLocationContainingIgnoreCase(location);
	}

	public Hotel createHotel(HotelDTO dto) {
		Hotel hotel = new Hotel();
		hotel.setName(dto.getName());
		hotel.setLocation(dto.getLocation());
		hotel.setPricePerNight(dto.getPricePerNight());
		hotel.setRating(dto.getRating());

		return hotelRepository.save(hotel);
	}
}
