package com.example.travel.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.CustomerDTO;
import com.example.travel.travel.service.CustomerService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private CustomerService customerService;

//	// ✅ Registration endpoint
//	@PostMapping("/register")
//	public String register(@RequestBody CustomerDTO customerDTO) {
//		customerService.registerCustomer(customerDTO);
//		return "Registration successful!";
//	}

	// ✅ Login success
	@GetMapping("/success")
	public String loginSuccess() {
		return "Login successful!";
	}

	// ✅ Login failure
	@GetMapping("/failure")
	public String loginFailure() {
		return "Login failed!";
	}

	// ✅ Logout success
	@GetMapping("/logout-success")
	public String logoutSuccess() {
		return "Logout successful!";
	}
}
