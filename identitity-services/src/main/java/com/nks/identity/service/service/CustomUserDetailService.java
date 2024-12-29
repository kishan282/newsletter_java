package com.nks.identity.service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nks.identity.service.dto.CustomUserDetails;
import com.nks.identity.service.entity.UserCredentials;
import com.nks.identity.service.repository.UserCredRepo;

@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserCredRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredentials> credentials = repo.findByEmailId(username);
		return credentials.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User Not found" + username));
	}

}
