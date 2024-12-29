package com.nks.identity.service.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nks.identity.service.dto.AuthRequest;
import com.nks.identity.service.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authservice;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public String getToken(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), 
						authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return authservice.generateToke(authRequest.getUsername());
		} else {
			throw new RuntimeException("Invalid Username/Password!");
		}
	}
}
