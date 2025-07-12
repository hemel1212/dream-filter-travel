package com.example.travel.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.travel.travel.DTO.LoginDTO;
import com.example.travel.travel.entity.Admin;
import com.example.travel.travel.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/login")
	public ResponseEntity<Admin> register(@RequestBody LoginDTO loginDTO) {
		Admin admin = adminService.login(loginDTO);
		return ResponseEntity.ok(admin);
	}
}
