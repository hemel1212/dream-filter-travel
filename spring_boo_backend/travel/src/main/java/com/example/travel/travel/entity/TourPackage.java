package com.example.travel.travel.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "HEMEL_TOUR_PACKAGE")
public class TourPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long packageId;

	private String name;
	private String description;
	private Double price;
	private Integer durationDays;
	@Column(length = 800)
	private String destination;

	private Date createdAt;

	@OneToMany(mappedBy = "tourPackage")
	@JsonManagedReference("package-itinerarie")
	private List<Itinerary> itineraries;

	@OneToMany(mappedBy = "tourPackage")
	@JsonManagedReference("package-bookings")
	private List<Booking> bookings;
}