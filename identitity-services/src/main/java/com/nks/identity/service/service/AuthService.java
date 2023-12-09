package com.nks.identity.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nks.identity.service.repository.UserCredRepo;

@Service
public class AuthService {
	
	@Autowired
	private UserCredRepo repository;
	
	@Autowired
	private JwtService jwtService;
	
	public String generateToke(String username) {
		return jwtService.generateToken(username);
	}
	
	public void validateToken(String token) {
		jwtService.validatToken(token);
	}

}
