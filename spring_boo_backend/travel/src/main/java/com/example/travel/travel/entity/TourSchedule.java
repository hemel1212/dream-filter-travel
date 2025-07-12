package com.example.travel.travel.entity;

import java.util.Date;

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
@Table(name = "HEMEL_TOUR_SCHEDULE")
public class TourSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scheduleId;

	@ManyToOne
	@JoinColumn(name = "package_id")
	private TourPackage tourPackage;

	@ManyToOne
	@JoinColumn(name = "guide_id")
	private TourGuide tourGuide;

	private Date tourDate;
	private String itineraryDetails;
}
