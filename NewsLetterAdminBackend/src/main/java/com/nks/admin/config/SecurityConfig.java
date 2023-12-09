package com.nks.admin.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nks.admin.filter.JwtAuthenticationFilter;
import com.nks.admin.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public UserDetailsService userDetailService() {
		return new CustomUserDetailService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests().antMatchers("/newsletter/allusers").permitAll()
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

}
