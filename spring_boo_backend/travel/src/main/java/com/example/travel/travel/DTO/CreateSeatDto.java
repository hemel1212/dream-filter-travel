package com.example.travel.travel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSeatDto {

	private String seatNumber;
	private Long transportId;
}