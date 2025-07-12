package com.example.travel.travel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportSeatDTO {
	private Long seatId;
	private String seatNumber;
	private Boolean isAvailable;
	private Long transportId;
}
