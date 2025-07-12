package com.example.travel.travel.service;

import com.example.travel.travel.DTO.CustomerDTO;
import com.example.travel.travel.entity.Customer;

public interface CustomerServiceLog {
	void registerCustomer(CustomerDTO customerDTO);

	Customer findByEmail(String email);
}
