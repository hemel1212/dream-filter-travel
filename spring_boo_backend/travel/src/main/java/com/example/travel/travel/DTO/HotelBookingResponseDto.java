package com.example.travel.travel.DTO;

import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HotelBookingResponseDto {
	
	private String customerName;
	private String customerEmail;
	private Date checkInDate;
	private Date checkOutDate;
	private Integer numRooms;

}
