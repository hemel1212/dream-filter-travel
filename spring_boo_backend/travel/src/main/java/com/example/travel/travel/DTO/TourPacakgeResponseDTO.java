package com.example.travel.travel.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourPacakgeResponseDTO {
	private Long packageId;

	private String name;
	private String description;
	private Double price;
	private Integer durationDays;
	private String destination;

	private Date createdAt;
	
	
}
