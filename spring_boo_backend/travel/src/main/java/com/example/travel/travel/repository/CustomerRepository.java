package com.example.travel.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travel.travel.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByEmail(String email);

	Optional<Customer> findByNameAndPasswordHash(String name, String password);
	
	Optional<Customer> findByEmailAndPasswordHash(String email, String password);

}