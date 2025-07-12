package com.example.travel.travel.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "HEMEL_BOOKING")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference("customer-bookings")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "package_id")
    @JsonBackReference("package-bookings")
    private TourPackage tourPackage;

    private LocalDate bookingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numPeople;
    private String status;
    private Double totalAmount;

    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;

    @OneToMany(mappedBy = "booking")
    private List<HotelBooking> hotelBookings;

    @OneToMany(mappedBy = "booking")
    private List<TransportBooking> transportBookings;
}

