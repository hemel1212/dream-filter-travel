package com.example.travel.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.LoginDTO;
import com.example.travel.travel.entity.Customer;
import com.example.travel.travel.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping("/register")
	public ResponseEntity<Customer> register(@RequestBody Customer customer) {
		Customer saveCustomer = customerService.registerCustomer(customer);
		return ResponseEntity.ok(saveCustomer);
	}

	@PostMapping("/login")
	public ResponseEntity<Customer> register(@RequestBody LoginDTO loginDTO) {
		Customer customer = customerService.login(loginDTO);
		return ResponseEntity.ok(customer);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		Customer getCustomer = customerService.getCustomerById(id);

		return ResponseEntity.ok(getCustomer);
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		return ResponseEntity.ok(customerService.updateCustomer(id, customer));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}
}
