package com.example.travel.travel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
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
@Table(name = "HEMEL_TRANSPORT_SEAT")
public class TransportSeat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seatId;

	@Column(unique = true)
	private String seatNumber; // A1, B2, etc.

	private Boolean isAvailable = true; // true = available, false = booked

	@ManyToOne
	@JoinColumn(name = "transport_id")
	@JsonBackReference
	private Transport transport;

}
