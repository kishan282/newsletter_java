package com.nks.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.nks.admin.model.UserCredentials;
import com.nks.admin.repositories.UserCredentialsRepo;

@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserCredentialsRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCredentials user = repo.findByEmailId(username);
		UserBuilder builder = null;
		builder = User.withUsername(username);
		builder.password(new BCryptPasswordEncoder().encode(user.getUserPassword()));
		System.out.println("ROLE====" + user.getUserRole().toUpperCase());
		// builder.roles(user.getRoles().iterator().next().getName().toString(),"ADMIN");
		builder.roles(user.getUserRole().toUpperCase());
		return builder.build();
	}

}
