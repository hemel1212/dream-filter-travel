package com.example.travel.travel.entity;

import java.util.List;

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
@Table(name = "HEMEL_TOUR_GUIDE")
public class TourGuide {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long guideId;

	private String name;
	private String experience;
	private Double rating;

	@OneToMany(mappedBy = "tourGuide")
	private List<TourSchedule> tourSchedules;
	
}
