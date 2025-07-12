package com.example.travel.travel.service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.travel.travel.DTO.CustomerDTO;
import com.example.travel.travel.entity.Customer;
import com.example.travel.travel.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long customerId) {
		return customerRepository.findById(customerId);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
		updatedCustomer.setCustomerId(customerId);
		return customerRepository.save(updatedCustomer);
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public void registerCustomer(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
