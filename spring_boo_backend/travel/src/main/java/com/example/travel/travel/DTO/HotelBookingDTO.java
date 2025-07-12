package com.example.travel.travel.DTO;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HotelBookingDTO {
	private Long customerId;
	private Long hotelId;
	private Date checkInDate;
	private Date checkOutDate;
	private Integer numRooms;



}
