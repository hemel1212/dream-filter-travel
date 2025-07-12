package com.example.travel.travel.entity;

import java.util.List;

import com.example.travel.travel.DTO.HotelDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "HEMEL_HOTEL")
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelId;

	private String name;
	private String location;
	private Double pricePerNight;
	private Integer rating;

	@OneToMany(mappedBy = "hotel")
	private List<HotelBooking> hotelBookings;

	public HotelDTO toHotelDto(){
		HotelDTO dto = new HotelDTO();

		dto.setHotelId(this.hotelId);
		dto.setName(this.name);
		dto.setLocation(this.location);
		dto.setPricePerNight(this.pricePerNight);
		dto.setRating(this.rating);

		return dto;
	}
}
