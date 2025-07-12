package com.example.travel.travel.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "HEMEL_TRANSPORT")
public class Transport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transportId;

	private String type; // Bus, Train, Flight
	private String provider;
	private Integer availableSeat;
	private Double price;

	@OneToMany(mappedBy = "transport")
	private List<TransportBooking> transportBookings;

	@OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<TransportSeat> transportSeats;

}