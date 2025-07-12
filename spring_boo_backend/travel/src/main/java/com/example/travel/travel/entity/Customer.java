package com.example.travel.travel.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "HEMEL_CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	private String name;
	@Column(unique = true)
	private String email;
	private String passwordHash;
	private String phone;
	private String address;

	private LocalDate createdAt = LocalDate.now();

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	@JsonManagedReference("customer-bookings")
	private List<Booking> bookings;

}
