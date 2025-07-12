package com.example.travel.travel.DTO;

import java.time.LocalDate;
import java.util.Date;

import com.example.travel.travel.entity.Customer;
import com.example.travel.travel.entity.TourPackage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {

	private Long bookingId;
	private Long packageId;
	private Long customerId;
	private TourPackage tourPackage;

	private LocalDate bookingDate;

	private LocalDate startDate;
	private LocalDate endDate;
	private Integer numPeople;
	private String status;
	private Double totalAmount;
}
