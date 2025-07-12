package com.example.travel.travel.DTO;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransportBookingDTO {
	private Long transportBookingId;
	private Long bookingId;
	private Long transportId;
	private Date departureDate;
	private Date arrivalDate;
	private Integer numSeats;
	private String status;
	private String seatPlan;
}
