package com.example.travel.travel.service.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.travel.travel.DTO.CustomerDTO;
import com.example.travel.travel.entity.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();

	Optional<Customer> getCustomerById(Long customerId);

	Customer createCustomer(Customer customer);

	Customer updateCustomer(Long customerId, Customer updatedCustomer);

	void deleteCustomer(Long customerId);

	void registerCustomer(CustomerDTO customerDTO);

	Customer findByEmail(String email);
}
