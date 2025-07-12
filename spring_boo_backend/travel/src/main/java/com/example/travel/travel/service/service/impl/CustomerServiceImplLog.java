package com.example.travel.travel.service.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.travel.travel.DTO.CustomerDTO;
import com.example.travel.travel.entity.Customer;
import com.example.travel.travel.repository.CustomerRepository;

@Service
public class CustomerServiceImplLog implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	//private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void registerCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customerRepository.save(customer);
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> getCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Long customerId) {
		// TODO Auto-generated method stub

	}
}
