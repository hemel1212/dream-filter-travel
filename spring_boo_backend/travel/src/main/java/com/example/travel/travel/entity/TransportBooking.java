package com.example.travel.travel.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "HEMEL_TRANSPORT_BOOKING")
public class TransportBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transportBookingId;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "booking_id")
	private Booking booking;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "transport_id")
	private Transport transport;

	private Date departureDate;
	private Date arrivalDate;
	private Integer numSeats;
	private String status;

	@Lob
	@Column(name = "seatPlan")
	private String seatPlan;

}