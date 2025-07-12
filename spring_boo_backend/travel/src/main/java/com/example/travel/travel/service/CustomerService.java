package com.example.travel.travel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.travel.travel.DTO.LoginDTO;
import com.example.travel.travel.entity.Customer;
import com.example.travel.travel.repository.CustomerRepository;

@Service
public class CustomerService {
	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(Long customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}

	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
		updatedCustomer.setCustomerId(customerId);
		return customerRepository.save(updatedCustomer);
	}

	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	public Customer registerCustomer(Customer customer) {
		customer.setCustomerId(null);
		return customerRepository.save(customer);
	}

	public Customer login(LoginDTO loginDTO) {
		Optional<Customer> customer = customerRepository.findByEmailAndPasswordHash(loginDTO.email(),
				loginDTO.password());
		if (customer.isPresent())
			return customer.get();
		else
			return null;
	}
}
