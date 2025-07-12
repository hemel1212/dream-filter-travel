package com.example.travel.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.Customer;

public interface CustomerRepositoryLog extends JpaRepository<Customer, Long> {
	Customer findByEmail(String email);
}
