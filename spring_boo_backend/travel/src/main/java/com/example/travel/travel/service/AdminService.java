package com.example.travel.travel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travel.travel.DTO.LoginDTO;
import com.example.travel.travel.entity.Admin;
import com.example.travel.travel.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;

	public Admin login(LoginDTO loginDTO) {
		Optional<Admin> admin = adminRepository.findByNameAndPasswordHash(loginDTO.email(), loginDTO.password());
		if (admin.isPresent())
			return admin.get();
		else
			return null;
	}

}
