package com.example.travel.travel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "HEMEL_ITINERARY")
public class Itinerary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itineraryId;

	@ManyToOne
	@JoinColumn(name = "package_id")
	@JsonBackReference("package-itinerarie")
	private TourPackage tourPackage;

	private Integer dayNumber;
	private String description;
	private String location;
}
